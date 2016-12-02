package me.j360.nio.nio2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

/**
 * Package: me.j360.nio.nio2
 * User: min_xu
 * Date: 2016/12/2 下午4:35
 * 说明：
 */
public class RafsTest {


    /**
     With a RAF, you can open the file, seek a particular location, and read from or write to that file. After you open a RAF,
     you can read from it or write to it in a random manner just by using a record number, or you can add to the beginning
     or end of the file since you know how many records are in the file. A RAF allows you to read a single character,
     read a chunk of bytes or a line, replace a portion of the file, append lines, delete lines,
     and so forth, and allows you to perform all of these actions in a random manner.

     fastest way to copy a file using FileChannel capabilities versus other common approaches, like Files.copy(), buffered streams, and so on.

     SeekableByteChannel带来的改进

     FileChannel直接内存 快于 非直接内容
     FileChannel transferTo 快于 transFrom 快于 map
     FilesCopy Path2Path 快于 Path2OutputStream 快于 InputStream2Path


     * @param args
     */
    public static void main(String[] args){
        Path path = Paths.get(System.getProperty("user.home"),"tmp","path.txt");

        try (SeekableByteChannel seekableByteChannel = Files.newByteChannel(path,
                EnumSet.of(StandardOpenOption.READ))) {
            ByteBuffer buffer = ByteBuffer.allocate(12);
            String encoding = System.getProperty("file.encoding");
            buffer.clear();
            while (seekableByteChannel.read(buffer) > 0) {
                buffer.flip();
                System.out.print(Charset.forName(encoding).decode(buffer));
                buffer.clear();
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }


    }
}
