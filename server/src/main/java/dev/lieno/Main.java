package dev.lieno;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Server started.");

        // Creation of server socket
        ServerSocket ss = new ServerSocket(3000);
        System.out.println("Listening on 0.0.0.0:" + ss.getLocalPort());
        
        // Creation of socket
        Socket s = ss.accept();
        System.out.println("Client connected.");
        
        // Socket input output
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);

        // Server logic:
        
        

        System.out.println("Client disconnected.");
        ss.close();
    }
}