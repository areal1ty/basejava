package com.basejava.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
    }

    public static void printDirectoryFiles(File directory) {
        File[] files = directory.listFiles();

        for (File file : Objects.requireNonNull(files)) {
            if (file.isDirectory()) {
                printDirectoryFiles(file);
            } else {
                System.out.println(file.getName());
            }
        }
    }
    }
