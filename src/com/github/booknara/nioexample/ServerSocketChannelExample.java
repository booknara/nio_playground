package com.github.booknara.nioexample;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by Daehee Han(@daniel_booknara) on 2/12/16.
 */
public class ServerSocketChannelExample {
    public static void main(String[] args) {
        listenBlockServerSocketChannel();
        listenNonBlockServerSocketChannel();
    }

    public static void listenBlockServerSocketChannel() {
        try {
            ServerSocketChannel serverSocketChannel = createServerSocketChannel(true);

            while (true) {
                SocketChannel socketChannel = serverSocketChannel.accept();

                // Do something...
            }

//            channel.close();
        } catch (IOException e) {
            e.getStackTrace();
        }

    }

    public static void listenNonBlockServerSocketChannel() {
        try {
            ServerSocketChannel serverSocketChannel = createServerSocketChannel(true);

            while (true) {
                SocketChannel socketChannel = serverSocketChannel.accept();

                // Need to check out incoming SocketChannel whether it's ready or not
                if (socketChannel != null) {
                    // Do something...
                }

            }

//            channel.close();
        } catch (IOException e) {
            e.getStackTrace();
        }

    }

    public static ServerSocketChannel createServerSocketChannel(boolean blockMode) throws IOException{
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(blockMode);
        channel.socket().bind(new InetSocketAddress(1025));

        return channel;
    }
}
