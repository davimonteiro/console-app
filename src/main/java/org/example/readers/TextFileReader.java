package org.example.readers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextFileReader implements SupportedFileReader {

    @Override
    public String readFile(Path path) throws IOException {
        return Files.readString(path);
    }

}
