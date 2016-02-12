package com.github.booknara.nioexample;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

/**
 * Created by Daehee Han(@daniel_booknara) on 2/11/16.
 */
public class ScatterGatherExample {
    public static void main(String[] args) {
        int headerNumber = 1;
        String bodyText = "This is a body";

        int count = 0;
        byte[] bytes = null;
        try {
            bytes = bodyText.getBytes("UTF-8");
            count = bytes.length;
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getStackTrace());
        }

        // Gather
        ByteBuffer header = ByteBuffer.allocate(4);
        ByteBuffer body = ByteBuffer.allocate(count);

        header.putInt(headerNumber);
        body.put(bytes);

        header.flip();
        body.flip();

        GatheringByteChannel gatheringByteChannel = createChannelInstance("test.txt", true);

        ByteBuffer[] bufferArray = { header, body };
        try {
            gatheringByteChannel.write(bufferArray);
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }

        // Scatter
        ByteBuffer newHeader = ByteBuffer.allocate(4);
        ByteBuffer newBody = ByteBuffer.allocate(count);

        ScatteringByteChannel scatteringByteChannel = createChannelInstance("test.txt", false);

        ByteBuffer[] newBufferArray = { newHeader, newBody };
        try {
            scatteringByteChannel.read(newBufferArray);
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }

        newHeader.rewind();
        newBody.rewind();

        int headerContent;
        String bodyContent = null;

        headerContent = newHeader.getInt();
        byte[] byteBodyContent = new byte[count];
        newBody.get(byteBodyContent);
        try {
            bodyContent = new String(byteBodyContent, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getStackTrace());
        }

        System.out.println("Header : " + headerContent);
        System.out.println("Body : " + bodyContent);

    }

    public static FileChannel createChannelInstance(String file, boolean isOutput) {
        FileChannel fileChannel = null;
        try {
            if (isOutput)
                fileChannel = new FileOutputStream(file).getChannel();
            else
                fileChannel = new FileInputStream(file).getChannel();
        } catch (FileNotFoundException e) {
            System.out.println(e.getStackTrace());
        }

        return fileChannel;
    }
}
