package me.j360.nio.nio2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Package: me.j360.nio.nio2
 * User: min_xu
 * Date: 2016/12/2 下午4:50
 * 说明：
 */
public class SocketsTest {


    /**
     All asynchronous I/O operations have one of two forms:
     • Pending result:Pending Result and the Future Class
     • Complete result:Complete Result and the CompletionHandler Interface(well-known callback mechanism)


     • AsynchronousFileChannel
     • AsynchronousServerSocketChannel
     • AsynchronousSocketChannel

     * @param args
     */
    public static void main(String[] args) throws IOException {
        socketServerChannelTest2();


        //or


        socketClientChannelTest2();

    }


    public static void fileChannelTest() throws IOException {
        Path path = Paths.get(System.getProperty("user.home"),"tmp","path.txt");

        ByteBuffer buffer = ByteBuffer.allocate(100);
        String encoding = System.getProperty("file.encoding");
        try (AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path,
                StandardOpenOption.READ)) {
            Future<Integer> result = asynchronousFileChannel.read(buffer, 0);
            while (!result.isDone()) {
                System.out.println("Do something else while reading ...");
            }
            System.out.println("Read done: " + result.isDone());
            System.out.println("Bytes read: " + result.get());
        } catch (Exception ex) {
            System.err.println(ex);
        }
        buffer.flip();
        System.out.print(Charset.forName(encoding).decode(buffer));
        buffer.clear();



        try (AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path,
                StandardOpenOption.WRITE)) {
            Future<Integer> result = asynchronousFileChannel.write(buffer, 100);
            while (!result.isDone()) {
                System.out.println("Do something else while writing ...");
            }
            System.out.println("Written done: " + result.isDone());
            System.out.println("Bytes written: " + result.get());
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }


    public static void fileChannelTest2() throws IOException {
        Thread current;

        Path path = Paths.get(System.getProperty("user.home"),"tmp","path.txt");
        ByteBuffer buffer = ByteBuffer.allocate(100);
        try (AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path,
                StandardOpenOption.READ)) {
            current = Thread.currentThread();
            asynchronousFileChannel.read(buffer, 0, "Read operation status ...", new
                    CompletionHandler<Integer, Object>() {
                        @Override
                        public void completed(Integer result, Object attachment) {
                            System.out.println(attachment);
                            System.out.print("Read bytes: " + result);
                            current.interrupt();
                        }
                        @Override
                        public void failed(Throwable exc, Object attachment) {
                            System.out.println(attachment);
                            System.out.println("Error:" + exc);
                            current.interrupt();
                        } });
            System.out.println("\nWaiting for reading operation to end ...\n");
            try {
                current.join();
            } catch (InterruptedException e) {
            }
            //now the buffer contains the read bytes
            System.out.println("\n\nClose everything and leave! Bye, bye ...");
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }



    public static void socketServerChannelTest() throws IOException {

        final int DEFAULT_PORT = 5555;
        final String IP = "127.0.0.1";
        //create an asynchronous server socket channel bound to the default group
        try (AsynchronousServerSocketChannel asynchronousServerSocketChannel =
                     AsynchronousServerSocketChannel.open()) {
            if (asynchronousServerSocketChannel.isOpen()) {
                //set some options
                asynchronousServerSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 4 * 1024);
                asynchronousServerSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
                //bind the asynchronous server socket channel to local address
                asynchronousServerSocketChannel.bind(new InetSocketAddress(IP, DEFAULT_PORT));
                //display a waiting message while ... waiting    clients
                System.out.println("Waiting for connections ...");
                while (true) {
                    Future<AsynchronousSocketChannel> asynchronousSocketChannelFuture =
                            asynchronousServerSocketChannel.accept();
                    try (AsynchronousSocketChannel asynchronousSocketChannel =
                                 asynchronousSocketChannelFuture.get()) {
                        System.out.println("Incoming connection from: " +
                                asynchronousSocketChannel.getRemoteAddress());
                        final ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
                        //transmitting data
                        while (asynchronousSocketChannel.read(buffer).get() != -1) {
                            buffer.flip();
                            asynchronousSocketChannel.write(buffer).get();
                            if (buffer.hasRemaining()) {
                                buffer.compact();
                            } else {
                                buffer.clear();
                            } }
                        System.out.println(asynchronousSocketChannel.getRemoteAddress() +
                                " was successfully served!");
                    } catch (IOException | InterruptedException | ExecutionException ex) {
                        System.err.println(ex);
                    } }
            } else {
                System.out.println("The asynchronous server-socket channel cannot be opened!");
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }


    public static void socketServerChannelTest2() throws IOException {
        final int DEFAULT_PORT = 5555;
        final String IP = "127.0.0.1";
        ExecutorService taskExecutor=
                Executors.newCachedThreadPool(Executors.defaultThreadFactory());
        //create asynchronous server socket channel bound to the default group
        try (AsynchronousServerSocketChannel asynchronousServerSocketChannel =
                     AsynchronousServerSocketChannel.open()) {
            if (asynchronousServerSocketChannel.isOpen()) {
                //set some options
                asynchronousServerSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 4 * 1024);
                asynchronousServerSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
                //bind the server socket channel to local address
                asynchronousServerSocketChannel.bind(new InetSocketAddress(IP, DEFAULT_PORT));
                //display a waiting message while ... waiting clients
                System.out.println("Waiting for connections ...");
                while (true) {
                    Future<AsynchronousSocketChannel> asynchronousSocketChannelFuture =
                            asynchronousServerSocketChannel.accept();
                    try {
                        final AsynchronousSocketChannel asynchronousSocketChannel =
                                asynchronousSocketChannelFuture.get();
                        Callable<String> worker = new Callable<String>() {
                            @Override
                            public String call() throws Exception {
                                String host = asynchronousSocketChannel.getRemoteAddress().toString();
                                System.out.println("Incoming connection from: " + host);
                                final ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
                                //transmitting data
                                while (asynchronousSocketChannel.read(buffer).get() != -1) {
                                    buffer.flip();
                                    asynchronousSocketChannel.write(buffer).get();
                                    if (buffer.hasRemaining()) {
                                        buffer.compact();
                                    } else {
                                        buffer.clear();
                                    } }
                                asynchronousSocketChannel.close();
                                System.out.println(host + " was successfully served!");
                                return host;
                            }
                        };
                        taskExecutor.submit(worker);
                    } catch (InterruptedException | ExecutionException ex) {
                        System.err.println(ex);
                        System.err.println("\n Server is shutting down ...");
                        //this will make the executor accept no new threads
                        // and finish all existing threads in the queue
                        taskExecutor.shutdown();
                        //wait until all threads are finished
                        while (!taskExecutor.isTerminated()) {
                        }
                        break; }
                }
            } else {
                System.out.println("The asynchronous server-socket channel cannot be opened!");
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }


    public static void socketClientChannelTest() throws IOException {
        final int DEFAULT_PORT = 5555;
        final String IP = "127.0.0.1";
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        ByteBuffer helloBuffer = ByteBuffer.wrap("Hello !".getBytes());
        ByteBuffer randomBuffer;
        CharBuffer charBuffer;
        Charset charset = Charset.defaultCharset();
        CharsetDecoder decoder = charset.newDecoder();
        //create an asynchronous socket channel bound to the default group
        try (AsynchronousSocketChannel asynchronousSocketChannel =
                     AsynchronousSocketChannel.open()) {
            if (asynchronousSocketChannel.isOpen()) {
                //set some options
                asynchronousSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 128 * 1024);
                asynchronousSocketChannel.setOption(StandardSocketOptions.SO_SNDBUF, 128 * 1024);
                asynchronousSocketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
                //connect this channel's socket
                Void connect = asynchronousSocketChannel.connect
                        (new InetSocketAddress(IP, DEFAULT_PORT)).get();
                if (connect == null) {
                    System.out.println("Local address: " +
                            asynchronousSocketChannel.getLocalAddress());
                    //transmitting data
                    asynchronousSocketChannel.write(helloBuffer).get();
                    while (asynchronousSocketChannel.read(buffer).get() != -1) {
                        buffer.flip();
                        charBuffer = decoder.decode(buffer);
                        System.out.println(charBuffer.toString());
                        if (buffer.hasRemaining()) {
                            buffer.compact();
                        } else {
                            buffer.clear();
                        }
                        int r = new Random().nextInt(100);
                        if (r == 50) {
                            System.out.println("50 was generated! Close the asynchronous socket channel!");
                            break;
                        } else {
                            randomBuffer = ByteBuffer.wrap("Random number:".concat(String.valueOf(r)).getBytes());
                            asynchronousSocketChannel.write(randomBuffer).get();
                        }
                    }
                } else {
                    System.out.println("The connection cannot be established!");
                }
            } else {
                System.out.println("The asynchronous socket channel cannot be opened!");
            }
        } catch (IOException | InterruptedException | ExecutionException ex) {
            System.err.println(ex);
        }
    }


    public static void socketClientChannelTest2() throws IOException {
        final int DEFAULT_PORT = 5555;
        final String IP = "127.0.0.1";
        //create an asynchronous socket channel bound to the default group
        try (AsynchronousSocketChannel asynchronousSocketChannel =
                     AsynchronousSocketChannel.open()) {
            if (asynchronousSocketChannel.isOpen()) {
                //set some options
                asynchronousSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 128 * 1024);
                asynchronousSocketChannel.setOption(StandardSocketOptions.SO_SNDBUF, 128 * 1024);
                asynchronousSocketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
                //connect this channel's socket
                asynchronousSocketChannel.connect(new InetSocketAddress(IP, DEFAULT_PORT), null,
                        new CompletionHandler<Void, Void>() {
                            final ByteBuffer helloBuffer = ByteBuffer.wrap("Hello !".getBytes());
                            final ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
                            CharBuffer charBuffer = null;
                            ByteBuffer randomBuffer;
                            final Charset charset = Charset.defaultCharset();
                            final CharsetDecoder decoder = charset.newDecoder();
                            @Override
                            public void completed(Void result, Void attachment) {
                                try {
                                    System.out.println("Successfully connected at: " +
                                            asynchronousSocketChannel.getRemoteAddress());
                                    //transmitting data
                                    asynchronousSocketChannel.write(helloBuffer).get();
                                    while (asynchronousSocketChannel.read(buffer).get() != -1) {
                                        buffer.flip();
                                        charBuffer = decoder.decode(buffer);
                                        System.out.println(charBuffer.toString());
                                        if (buffer.hasRemaining()) {
                                            buffer.compact();
                                        } else {
                                            buffer.clear();
                                        }
                                        int r = new Random().nextInt(100);
                                        if (r == 50) {
                                            System.out.println("50 was generated! Close the asynchronous socket channel!");
                                            break;
                                        } else {
                                            randomBuffer = ByteBuffer.wrap("Random number:".concat(String.valueOf(r)).getBytes());
                                            asynchronousSocketChannel.write(randomBuffer).get();
                                        } }
                                } catch (IOException | InterruptedException | ExecutionException ex) {
                                    System.err.println(ex);
                                } finally {
                                    try {
                                        asynchronousSocketChannel.close();
                                    } catch (IOException ex) {
                                        System.err.println(ex);
                                    }
                                } }
                            @Override
                            public void failed(Throwable exc, Void attachment) {
                                throw new UnsupportedOperationException("Connection cannot be established!");
                            }
                        });
                System.in.read();
            } else {
                System.out.println("The asynchronous socket channel cannot be opened!");
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }




}
