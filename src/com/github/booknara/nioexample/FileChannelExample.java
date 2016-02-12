package com.github.booknara.nioexample;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;

/**
 * Created by Daehee Han(@daniel_booknara) on 2/12/16.
 */
public class FileChannelExample {
    public static void main(String[] args) {
        String data = "Today is " + getDate(System.currentTimeMillis());
        RandomAccessFile file;
        try {
            file = new RandomAccessFile("new_file.txt", "rw");
            FileChannel channel = file.getChannel();
            System.out.println("File size : " + channel.size());

            // Allocate ByteBuffer size dynamically
            ByteBuffer buffer = ByteBuffer.allocate(data.getBytes().length);
            System.out.println("Buffer allocation size : " + data.getBytes().length);

            buffer.clear();
            buffer.put(data.getBytes());

            buffer.flip();

            while (buffer.hasRemaining()) {
                channel.write(buffer);
                System.out.println("Channel position : " + channel.position());
            }

            channel.close();
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getStackTrace());
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }

    public static String getDate(long time) {
        Date date = new Date(time);
        return date.toString();
    }
}
