package me.j360.nio.nio2;

import java.io.File;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Package: me.j360.nio.nio2
 * User: min_xu
 * Date: 2016/12/2 下午3:08
 * 说明：
 */
public class PathsTest {


    public static void main(String[] args){
        File file = new File("/Users/min_xu/tmp");
        file.toPath();
        file.toURI();

        FileSystem fileSystem = FileSystems.getDefault();
        System.out.println(fileSystem.getFileStores().iterator().next().name());

        Path path = Paths.get("./").normalize();
        System.out.println(path.getParent());


        Path path1 = Paths.get("/Users/min_xu/tmp","path.txt").normalize();
        System.out.println(path1.getParent());

        Path path2 = Paths.get("path.txt").normalize();
        System.out.println(path2.getParent());


        //Path with URI
        Path path3 = Paths.get(URI.create("file:///tmp/path.txt"));
        System.out.println(path3.getParent());


        //Home Direct
        Path path4 = Paths.get(System.getProperty("user.home"),"tmp","path.txt");
        System.out.println(path4.getParent());
        System.out.println(path4.toFile());
        System.out.println(path4.toUri());
        System.out.println(path4.toAbsolutePath());
        System.out.println(path4.toString());


        System.out.println(path4.equals(path1));
    }

}
