package com.github.booknara.nioexample;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by Daehee Han(@daniel_booknara) on 2/16/16.
 */
public class AsynchronousFileChannelExample {
    public static void main(String[] args) throws IOException {
        // Reading an operation
        Path readFilePath = Paths.get(".", "colors.xml");

        AsynchronousFileChannel readFileChannel = AsynchronousFileChannel.open(readFilePath, StandardOpenOption.READ);
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        readBuffer.clear();

        readFileChannel.read(readBuffer, 0, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("Result = " + result);

                attachment.flip();
                byte[] data = new byte[attachment.limit()];
                attachment.get(data);
                System.out.println("Read String : " + new String(data));
                attachment.clear();
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });

        Path writeFilePath = Paths.get(".", "file-write.txt");
        if (!Files.exists(writeFilePath)) {
            Files.createFile(writeFilePath);
        }

        AsynchronousFileChannel writeFileChannel = AsynchronousFileChannel.open(writeFilePath, StandardOpenOption.WRITE);
        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
        writeBuffer.clear();
        String text = "New write text";

        writeBuffer.put(text.getBytes());
        writeBuffer.flip();

        writeFileChannel.write(writeBuffer, 0, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("byte lengh : " + result);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });

        while (true) {

        }
    }
}
