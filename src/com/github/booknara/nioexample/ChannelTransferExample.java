package com.github.booknara.nioexample;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * Created by Daehee Han(@daniel_booknara) on 2/11/16.
 */
public class ChannelTransferExample {
    public static void main(String[] args) {
        transferFrom();
        transferTo();
    }


    public static void transferFrom() {
        try {
            RandomAccessFile srcFile = new RandomAccessFile("colors.xml", "rw");
            FileChannel srcChannel = srcFile.getChannel();

            RandomAccessFile descFile = new RandomAccessFile("colors_1.xml", "rw");
            FileChannel descChannel = descFile.getChannel();

            long position = 0;

            descChannel.transferFrom(srcChannel, position, srcChannel.size());
        } catch (FileNotFoundException e) {
            System.out.println(e.getStackTrace());
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }

    }

    public static void transferTo() {
        try {
            RandomAccessFile srcFile = new RandomAccessFile("colors_1.xml", "rw");
            FileChannel srcChannel = srcFile.getChannel();

            RandomAccessFile descFile = new RandomAccessFile("colors_2.xml", "rw");
            FileChannel descChannel = descFile.getChannel();

            long position = 0;

            srcChannel.transferTo(position, srcChannel.size(), descChannel);
        } catch (FileNotFoundException e) {
            System.out.println(e.getStackTrace());
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }

    }

}
