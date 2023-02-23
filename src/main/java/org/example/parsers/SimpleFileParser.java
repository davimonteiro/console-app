package org.example.parsers;

import org.apache.commons.io.FilenameUtils;
import org.example.readers.FileExtension;
import org.example.readers.PdfFileReader;
import org.example.readers.SupportedFileReader;
import org.example.readers.TextFileReader;
import org.example.readers.WordFileReadier;
import org.example.statistics.MostUsedWordStatistic;
import org.example.statistics.NumberWordStatistic;
import org.example.statistics.SpecialCharacterStatistic;

import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

public class SimpleFileParser implements FileParser {

    private static final String PROCESSED = "processed";

    private final Path inputPath;

    private final Path outputPath;

    private final WatchService watchService;
    private final Map<FileExtension, SupportedFileReader> supportedExtensions;

    public SimpleFileParser(Path inputPath) throws IOException {
        assertInputPath(inputPath);
        this.inputPath = inputPath;
        this.outputPath = Paths.get(inputPath.toString(), PROCESSED);
        this.watchService = FileSystems.getDefault().newWatchService();
        this.supportedExtensions = new HashMap<>();
        this.supportedExtensions.put(FileExtension.TXT, new TextFileReader());
        this.supportedExtensions.put(FileExtension.DOCX, new WordFileReadier());
        this.supportedExtensions.put(FileExtension.PDF, new PdfFileReader());
    }

    public void monitor() throws IOException, InterruptedException {
        try (Stream<Path> paths = Files.list(inputPath)) {
            paths.forEach(path -> {
                if (!Files.isDirectory(path)) {
                    try {
                        process(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        inputPath.register(watchService, ENTRY_CREATE);
        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                Path path = Paths.get(inputPath.toString(), event.context().toString());
                process(path);
            }
            key.reset();
        }
        watchService.close();
    }

    private void process(Path file) throws IOException {
        if (isSupportedExtension(file)) {
            printStatistics(file);
            move(file);
        }
    }

    private void assertInputPath(Path inputPath) {
        if (!Files.isDirectory(inputPath)) {
            throw new IllegalArgumentException("The input path is not a directory.");
        }
    }

    private boolean isSupportedExtension(Path path) {
        return this.supportedExtensions.containsKey(getFileExtension(path));
    }

    private FileExtension getFileExtension(Path path) {
        return FileExtension.getFileExtension(FilenameUtils.getExtension(path.toString()));
    }

    public void move(Path file) throws IOException {
        if (!Files.isDirectory(outputPath)) {
            Files.createDirectory(outputPath);
        }
        Path target = Paths.get(outputPath.toString(), file.getFileName().toString());
        Files.move(file, target, StandardCopyOption.REPLACE_EXISTING);
    }

    private void printStatistics(Path path) throws IOException {
        SupportedFileReader supportedFileReader = supportedExtensions.get(getFileExtension(path));
        String lines = supportedFileReader.readFile(path);
        System.out.println("The number of words of " + path.getFileName() + ": " + new NumberWordStatistic().compute(lines));
        System.out.println("The number of dots of " + path.getFileName() + ": " + new SpecialCharacterStatistic().compute(lines));
        System.out.println("The most used word of " + path.getFileName() + ": " + new MostUsedWordStatistic().compute(lines));
    }

}
