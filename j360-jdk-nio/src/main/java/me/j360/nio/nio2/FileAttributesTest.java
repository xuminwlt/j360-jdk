package me.j360.nio.nio2;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.Set;

/**
 * Package: me.j360.nio.nio2
 * User: min_xu
 * Date: 2016/12/2 下午3:57
 * 说明：
 */
public class FileAttributesTest {

    /*
    Basic attribute names are listed here:
• lastModifiedTime
• lastAccessTime
• creationTime
• size
• isRegularFile
• isDirectory
• isSymbolicLink
• isOther
• fileKey
    * */

    public static void main(String[] args){
        FileSystem fs = FileSystems.getDefault();
        Set<String> views = fs.supportedFileAttributeViews();
        for (String view : views) {
            System.out.println(view);
        }
    }
}
