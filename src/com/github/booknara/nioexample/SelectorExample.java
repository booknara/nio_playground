package com.github.booknara.nioexample;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Daehee Han(@daniel_booknara) on 2/12/16.
 * This example is an incomplete code.
 */
public class SelectorExample {
    public static void main(String[] args) {
        Selector selector;
        // Define socket channel
//         SocketChannel channel = new SocketChannel();
//
//        try {
//            selector = Selector.open();
//            channel.configureBlocking(false);
//
//            SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_READ);
//
//
//            while(true) {
//
//                // Return how many channels are ready to read
//                int readyChannels = selector.select();
//
//                if(readyChannels == 0)
//                    continue;
//
//
//                Set<SelectionKey> selectedKeys = selector.selectedKeys();
//
//                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
//
//                while(keyIterator.hasNext()) {
//
//                    SelectionKey key = keyIterator.next();
//
//                    if(key.isAcceptable()) {
//                        // a connection was accepted by a ServerSocketChannel.
//                        // ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
//                        // SocketChannel socketChannel = (SocketChannel) key.channel();
//
//                    } else if (key.isConnectable()) {
//                        // a connection was established with a remote server.
//
//                    } else if (key.isReadable()) {
//                        // a channel is ready for reading
//
//                    } else if (key.isWritable()) {
//                        // a channel is ready for writing
//                    }
//
//                    keyIterator.remove();
//                }
//            }
//
//        } catch (IOException e) {
//            System.out.println(e.getStackTrace());
//        }

    }
}
