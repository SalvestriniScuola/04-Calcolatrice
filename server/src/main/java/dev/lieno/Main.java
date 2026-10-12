package dev.lieno;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final ExecutorService threadPool = Executors.newFixedThreadPool(10); 

    public static void main(String[] args) throws IOException {
        System.out.println("Server started.");

        // Creation of server socket
        ServerSocket ss = new ServerSocket(3000);
        ss.setReuseAddress(true);
        System.out.println("Listening on 0.0.0.0:" + ss.getLocalPort());
        
        
        while (true) {
            
            // Creation of socket
            Socket s = ss.accept();
            System.out.println("New client connected" + s.getInetAddress().getHostAddress());   
                                         
            ClientHandler clientSock = new ClientHandler(s);

            threadPool.execute(clientSock);
        }        
    }


}