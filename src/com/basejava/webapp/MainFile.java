package com.basejava.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class MainFile {

    public static void main(String[] args) {
        String filePath = ".//.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src/com/basejava/webapp");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        printDirectoryFiles(dir);
        printFilesWithIndentation(dir);

        File directory = new File("C:\\Users\\User\\Projects\\basejava\\storage");
        System.out.println(directory.getAbsolutePath());
    }

    public static void printDirectoryFiles(File directory) {
        File[] files = directory.listFiles();
        Arrays.stream(Objects.requireNonNull(files))
                .forEach(file -> {
                    if (file.isDirectory()) {
                        printDirectoryFiles(file);
                    } else {
                        System.out.println(file.getName());
                    }
                });
    }

    public static void printFilesWithIndentation(File directory) {
        printFilesWithIndentation(directory, 0);
    }

    private static void printFilesWithIndentation(File directory, int level) {
        File[] files = directory.listFiles();
        Arrays.stream(Objects.requireNonNull(files))
                .forEach(file -> {
                    if (file.isDirectory()) {
                        System.out.println(getIndentation(level) + file.getName());
                        printFilesWithIndentation(file, level + 1);
                    } else {
                        System.out.println(getIndentation(level) + file.getName());
                    }
                });
    }

    private static String getIndentation(int level) {
        return "\t".repeat(level);
    }
}
