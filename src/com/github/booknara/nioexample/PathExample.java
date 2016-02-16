package com.github.booknara.nioexample;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Daehee Han(@daniel_booknara) on 2/16/16.
 */
public class PathExample {
    public static void main(String[] args) {
        Path currentPath = Paths.get(".");
        System.out.println("Current path : " + currentPath.toAbsolutePath().toString());

        Path filePath = Paths.get(".", "colors.xml");
        System.out.println("File path : " + filePath.toAbsolutePath().toString());

        Path normalizedPath = filePath.normalize();
        System.out.println("Normalized File path : " + normalizedPath.toAbsolutePath().toString());
        System.out.println("Normalized File name : " + normalizedPath.getFileName());
        System.out.println("Normalized File name(0) : " + normalizedPath.getName(0));

        File file = normalizedPath.toFile();
        if (file.exists())
            System.out.println("The file exists!");
        else
            System.out.println("The file doesn't exist!");
    }
}
