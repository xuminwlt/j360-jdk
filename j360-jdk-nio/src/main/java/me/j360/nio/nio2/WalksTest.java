package me.j360.nio.nio2;

import java.io.IOException;
import java.nio.file.*;
import java.util.EnumSet;

/**
 * Package: me.j360.nio.nio2
 * User: min_xu
 * Date: 2016/12/2 下午4:23
 * 说明：
 */
public class WalksTest {

    //在静态方法中初始化内部类需要先实例化外部类 然后再实例化内部类。

    public static void main(String[] args){
        Path path4 = Paths.get(System.getProperty("user.home"),"tmp");
        ListTree walk = new WalksTest().new ListTree();                          //instantiate the walk
        EnumSet opts = EnumSet.of(FileVisitOption.FOLLOW_LINKS); //follow links
        try{
            Files.walkFileTree(path4, opts, Integer.MAX_VALUE, walk); //start the walk
        } catch(IOException e){
            System.err.println(e);
        }
    }


    class ListTree extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
            System.out.println("Visited directory: " + dir.toString());
            return FileVisitResult.CONTINUE;
        }
        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            System.out.println(exc);
            return FileVisitResult.CONTINUE;
        }
    }
}
