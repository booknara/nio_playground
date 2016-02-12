package com.github.booknara.nioexample;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Daehee Han(@daniel_booknara) on 2/11/16.
 */
public class ByteBufferExample {
    private static final int SIZE_BYTE = 48;

    public static void main(String[] args) {

        try {
            RandomAccessFile file = new RandomAccessFile("colors.xml", "rw");
            FileChannel channel = file.getChannel();

            // Create buffer with capacity of 48 bytes (fixed size)
            ByteBuffer buf = ByteBuffer.allocate(SIZE_BYTE);

            // Read into buffer
            int byteRead = channel.read(buf);
            System.out.println("Read byte : " + byteRead);

            while (byteRead != -1) {
                buf.flip();

                while (buf.hasRemaining()) {
                    System.out.print((char)buf.get());
                }

                // Make buffer ready for writing
                buf.clear();
                byteRead = channel.read(buf);
            }

            channel.close();
            file.close();
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }


    }
}
