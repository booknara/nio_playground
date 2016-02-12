package com.github.booknara.nioexample;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by Daehee Han(@daniel_booknara) on 2/12/16.
 */
public class SocketChannelExample {
    public static final String URL_ADDRESS = "http://www.google.com";
    public static final int SIZE = 48;
    public static final int PORT = 80;

    public static void main(String[] args) {
        try {
            // Default SocketChannel mode is blocking mode
            SocketChannel blockingChannel = createSocketChannel(true);

            readSocketChannel(blockingChannel);
            writeSocketChannel(blockingChannel);

            blockingChannel.close();


            SocketChannel nonBlockingChannel = createSocketChannel(false);
            while (!nonBlockingChannel.finishConnect()) {
                // Do something...
            }

            readSocketChannel(nonBlockingChannel);
            writeSocketChannel(nonBlockingChannel);

            nonBlockingChannel.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    // SocketChannel -> ByteBuffer
    public static void readSocketChannel(SocketChannel channel) {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(SIZE);
            int bytesRead = channel.read(buffer);

        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    // String -> ByteBuffer -> SocketChannel
    public static void writeSocketChannel(SocketChannel channel) {
        try {
            String data = "Response data to a client";

            ByteBuffer buffer = ByteBuffer.allocate(data.getBytes().length);
            buffer.clear();
            buffer.put(data.getBytes());
            buffer.flip();

            while (buffer.hasRemaining()) {
                channel.write(buffer);
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static SocketChannel createSocketChannel(boolean blockMode) throws IOException {
        // Default SocketChannel mode is blocking mode
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(blockMode);

        InetSocketAddress address = new InetSocketAddress(URL_ADDRESS, PORT);
        channel.connect(address);

        return channel;
    }
}
