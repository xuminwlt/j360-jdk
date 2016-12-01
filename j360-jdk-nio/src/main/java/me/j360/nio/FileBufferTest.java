package me.j360.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

/**
 * Package: me.j360.nio
 * User: min_xu
 * Date: 2016/11/21 下午7:56
 * 说明：
 */
public class FileBufferTest {


    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("/Users/min_xu/tmp/subtitle.csv", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);

            //首先读取数据到Buffer，然后反转Buffer,接着再从Buffer中读取数据
            buf.flip();

            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }


    public static void file(){
        File file = new File("");
        FileSystem fileSystem = FileSystems.getFileSystem(null);
        fileSystem.getRootDirectories();


        //Files.newByteChannel();
    }
}
