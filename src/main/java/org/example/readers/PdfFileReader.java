package org.example.readers;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PdfFileReader implements SupportedFileReader {

    @Override
    public String readFile(Path path) throws IOException {
        try (PDDocument doc = PDDocument.load(Files.newInputStream(path))) {
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            return pdfTextStripper.getText(doc);
        }
    }

}
