package com.github.booknara.nioexample;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by zeropc on 2/16/16.
 */
public class FilesExample {
    public static void main(String[] args) {
        Path path = Paths.get(".", "colors.xml");

        boolean exist = Files.exists(path, new LinkOption[] { LinkOption.NOFOLLOW_LINKS } );
        if (exist)
            System.out.println("The file exists!");
        else
            System.out.println("The file doesn't exist!");

        Path copyFile = Paths.get(".", "colors-copy.xml");
        Path moveFile = Paths.get(".", "colors-move.xml");
        try {
            // Copy operation
            Files.copy(path, copyFile);
            // Files.copy(path, copyFile, StandardCopyOption.REPLACE_EXISTING);

            // Move operation
            Files.move(path, moveFile, StandardCopyOption.REPLACE_EXISTING);

            // Delete operation
            Files.delete(copyFile);
            Files.delete(moveFile);
        } catch (FileAlreadyExistsException e) {
            System.out.println(e.getStackTrace());
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }

        Path subPath = Paths.get(".", "subdir");

        try {
            Path newPath = Files.createDirectory(subPath);
            boolean folderExist = Files.exists(newPath, new LinkOption[] { LinkOption.NOFOLLOW_LINKS } );
            if (folderExist)
                System.out.println("The new folder exists!");
            else
                System.out.println("The new folder doesn't exist!");

        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }

        Path currentPath = Paths.get(".");

        try {
            Files.walkFileTree(currentPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return super.preVisitDirectory(dir, attrs);
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println("File name : " + file.toAbsolutePath().toString());
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return super.visitFileFailed(file, exc);
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return super.postVisitDirectory(dir, exc);
                }
            });
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }


    }
}
