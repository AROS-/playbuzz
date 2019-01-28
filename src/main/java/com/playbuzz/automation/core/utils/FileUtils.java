package com.playbuzz.automation.core.utils;


import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.stream.Collectors;

public class FileUtils {

    private FileUtils() {}

    public static String readFile(String filePath) {
        try {
            return Files.lines(Paths.get(filePath)).collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException("File can not be read");
        }
    }

    public static void writeFile(String filePath, String fileContents) {
        try {
            Files.newBufferedWriter(Paths.get(filePath)).write(fileContents);
        } catch (IOException e) {
            throw new RuntimeException("Can not write into the file");
        }
    }

}
