package me.j360.nio.nio2;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;

/**
 * Package: me.j360.nio.nio2
 * User: min_xu
 * Date: 2016/12/2 下午3:59
 * 说明：
 */
public class FilesTest {

    public static void main(String[] args){
        try {
            adsu();
        }catch (Exception e){

        }



        final String tmp_dir_prefix = "rafa_";
        try {
//create a tmp directory in the base dir
            final Path tmp_dir = Files.createTempDirectory(tmp_dir_prefix);
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    System.out.println("Deleting the temporary folder ...");
                    try (DirectoryStream<Path> ds = Files.newDirectoryStream(tmp_dir)) {
                        for (Path file : ds) {
                            Files.delete(file);
                        }
                        Files.delete(tmp_dir);
                    } catch (IOException e) {
                        System.err.println(e);
                    }
                    System.out.println("Shutdown-hook completed...");
                }
            });
//simulate some I/O operations over the temporary file by sleeping 10 seconds
//when the time expires, the temporary file is deleted
            Thread.sleep(10000);
//operations done
        } catch (IOException | InterruptedException e) {
            System.err.println(e);
        }


    }


    public static void filesTest(){
        Path path = Paths.get(System.getProperty("user.home"),"tmp","path.txt");
        System.out.println(Files.exists(path));

        boolean is_readable = Files.isReadable(path);
        boolean is_writable = Files.isWritable(path);
        boolean is_executable = Files.isExecutable(path);
        boolean is_regular = Files.isRegularFile(path, LinkOption.NOFOLLOW_LINKS);
        if ((is_readable) && (is_writable) && (is_executable) && (is_regular)) {
            System.out.println("The checked file is accessible!");
        } else {
            System.out.println("The checked file is not accessible!");
        }

        try {
            boolean is_hidden = Files.isHidden(path);
            System.out.println("Is hidden ? " + is_hidden);
        } catch (IOException e) {
            System.err.println(e);
        }

        try {
            Files.createDirectory(path.getParent());
        } catch (IOException e) {
            System.err.println(e);
        }

        //no filter applied
        System.out.println("\nNo filter applied:");
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(path.getParent())) {
            for (Path file : ds) {
                System.out.println(file.getFileName());
            }
        }catch(IOException e) {
            System.err.println(e);
        }


        try {
            Files.createFile(path);
        } catch (IOException e) {
            System.err.println(e);
        }



        /*
        Reading a Small File
NIO.2 provides a quick method to read small byte/text files in a single shot. This facility is provided through the Files.readAllBytes() and Files.readAllLines() methods.
These methods read the entire file’s bytes or lines,
respectively, into a single read and take care of opening and closing the stream for you after the file has been read or an I/O error or exception has occurred.
        **/
        Charset charset = Charset.forName("UTF-8");
        ArrayList<String> lines = new ArrayList<>();
        lines.add("\n");
        lines.add("Rome Masters - 5 titles in 6 years");
        lines.add("Monte Carlo Masters - 7 consecutive titles (2005-2011)");
        lines.add("Australian Open - Winner 2009");
        lines.add("Roland Garros - Winner 2005-2008, 2010, 2011");
        lines.add("Wimbledon - Winner 2008, 2010");
        lines.add("US Open - Winner 2010");
        try {
            Files.write(path, lines, charset, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println(e);
        }



        //newBufferedWriter
        //newBufferedReader
        String text = "\nVamos Rafa!";
        try (BufferedWriter writer = Files.newBufferedWriter(path, charset,
                StandardOpenOption.APPEND)) {
            writer.write(text);
        } catch (IOException e) {
            System.err.println(e);
        }
        try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println(e);
        }


        //newOutputStream
        //newInputStream
        String string = "\nString: Babolat RPM Blast 16";
        try (
                OutputStream outputStream = Files.newOutputStream(path, StandardOpenOption.APPEND);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {
            writer.write(string);
        } catch (IOException e) {
            System.err.println(e);
        }

        int n;
        try (InputStream in = Files.newInputStream(path)) {
            while ((n = in.read()) != -1) {
                System.out.println((char)n);
            }
        } catch (IOException e) {
            System.err.println(e);
        }

        byte[] in_buffer = new byte[1024];
        try (InputStream in = Files.newInputStream(path)) {
            while ((n = in.read(in_buffer)) != -1) {
                System.out.println(new String(in_buffer));
            }
        } catch (IOException e) {
            System.err.println(e);
        }


        try (InputStream in = Files.newInputStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void tmpFileTest(){

        String default_tmp = System.getProperty("java.io.tmpdir");
        System.out.println(default_tmp);

        String tmp_dir_prefix = "nio_";
        try {
            //passing null prefix
            Path tmp_1 = Files.createTempDirectory(null);
            System.out.println("TMP: " + tmp_1.toString());
            //set a prefix
            Path tmp_2 = Files.createTempDirectory(tmp_dir_prefix);
            System.out.println("TMP: " + tmp_2.toString());
        } catch (IOException e) {
            System.err.println(e);
        }
    }


    //Files 增删改查
    public static void adsu() throws IOException {
        Path path = Paths.get(System.getProperty("user.home"),"tmp","path.txt");
        Path path2 = Paths.get(System.getProperty("user.home"),"tmp","path2.txt");
        System.out.println(Files.exists(path));
        Files.createFile(path);
        Files.move(path,path2);
        Files.copy(path,path2);
        Files.delete(path);
    }
}
