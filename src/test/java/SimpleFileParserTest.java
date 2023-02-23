import org.example.parsers.FileParser;
import org.example.parsers.SimpleFileParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SimpleFileParserTest {

    @Disabled
    @Test
    public void test(@TempDir Path inputPath) throws IOException, InterruptedException {
        // Given
        Path file1 = Paths.get("src", "test", "resources", "file1.txt");
        Files.copy(file1, Path.of(inputPath.toString(), file1.getFileName().toString()));
        FileParser fileParser = new SimpleFileParser(inputPath);

        // When
        fileParser.monitor();

        // Then
        Path processedPath = Path.of(inputPath.toString(), "processed");
        Assertions.assertEquals(1, Files.list(processedPath).toList().size());
    }

}
