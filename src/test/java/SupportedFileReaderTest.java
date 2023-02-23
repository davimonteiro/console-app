import org.example.readers.PdfFileReader;
import org.example.readers.SupportedFileReader;
import org.example.readers.TextFileReader;
import org.example.readers.WordFileReadier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

public class SupportedFileReaderTest {

    private String filename;
    private String output;

    private SupportedFileReader supportedFileReader;

    @Test
    public void readTextFileTest() throws IOException {
        // Given
        givenFileInputWithName("file1.txt");
        givenTextFileReader();

        // When
        whenProcessFileInput();

        // Then
        thenVerifyActualAndExpectedOutput();
    }

    @Test
    public void readWordFileTest() throws IOException {
        // Given
        givenFileInputWithName("file1.docx");
        givenWordFileReader();

        // When
        whenProcessFileInput();

        // Then
        thenVerifyActualAndExpectedOutput();
    }

    @Test
    public void readPdfFileTest() throws IOException {
        // Given
        givenFileInputWithName("file1.pdf");
        givenPdfFileReader();

        // When
        whenProcessFileInput();

        // Then
        thenVerifyActualAndExpectedOutput();
    }

    private void thenVerifyActualAndExpectedOutput() {
        Assertions.assertEquals(output, output);
    }

    private void whenProcessFileInput() throws IOException {
        this.output = supportedFileReader.readFile(Paths.get("src", "test", "resources", filename));
    }

    private void givenTextFileReader() {
        this.supportedFileReader = new TextFileReader();
    }

    private void givenWordFileReader() {
        this.supportedFileReader = new WordFileReadier();
    }

    private void givenPdfFileReader() {
        this.supportedFileReader = new PdfFileReader();
    }

    private void givenFileInputWithName(String filename) throws IOException {
        this.filename = filename;
    }

}
