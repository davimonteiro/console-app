package org.example.readers;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WordFileReadier implements SupportedFileReader {

    @Override
    public String readFile(Path path) throws IOException {
        try (XWPFDocument doc = new XWPFDocument(Files.newInputStream(path))) {
            XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(doc);
            return xwpfWordExtractor.getText();
        }
    }

}
