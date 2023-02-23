package org.example.readers;

import java.io.IOException;
import java.nio.file.Path;

public interface SupportedFileReader {

    String readFile(Path path) throws IOException;

}
