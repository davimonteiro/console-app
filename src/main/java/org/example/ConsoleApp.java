package org.example;

import org.example.parsers.FileParser;
import org.example.parsers.SimpleFileParser;

import java.io.IOException;
import java.nio.file.Paths;

public class ConsoleApp {

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length == 0) {
            System.out.println("No input path provided.");
            System.exit(0);
        }
        FileParser fileParser = new SimpleFileParser(Paths.get(args[0]));
        fileParser.monitor();
    }

}
