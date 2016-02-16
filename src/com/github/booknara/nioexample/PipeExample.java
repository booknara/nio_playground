package com.github.booknara.nioexample;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * Created by Daehee Han(@daniel_booknara) on 2/16/16.
 */
public class PipeExample {
    public static void main(String[] args) throws IOException{
        Pipe pipe = Pipe.open();

        Pipe.SinkChannel sinkChannel = pipe.sink();

        String text = "This is a text";

        ByteBuffer buffer = ByteBuffer.allocate(48);
        buffer.clear();

        buffer.put(text.getBytes());
        System.out.println("Length : " + text.getBytes().length);
        System.out.println("Buffer Limit : " + buffer.limit());

        while (buffer.hasRemaining()) {
            int length = sinkChannel.write(buffer);
            System.out.println("Write length : " + length);
        }

        Pipe.SourceChannel sourceChannel = pipe.source();
        buffer = ByteBuffer.allocate(48);

        int bytesRead = sourceChannel.read(buffer);
        System.out.println("Read length : " + bytesRead);

//        while (sourceChannel.read(buffer) > 0) {
//            buffer.flip();
//
//            while (buffer.hasRemaining()) {
//                char ch = buffer.getChar();
//                System.out.print(ch);
//            }
//
//            buffer.clear();
//        }
    }
}
