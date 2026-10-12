package dev.lieno;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        
        String text;
        do {
            text = in.readLine();
            
            String result;
            
            if(text.contains("+"))
                result = calc(text, "+");
            else if(text.contains("-"))
                result = calc(text, "-");
            else if(text.contains("/"))
                result = calc(text, "/");
            else if(text.contains("*"))
                result = calc(text, "*");
            else
                result = "Error!";

            out.println(result);

        } while (!text.equals("exit"));
        

        System.out.println("Client disconnected.");
        ss.close();
    }

    static private String calc(String in, String operator) {
        Double num1 = Double.parseDouble(in.split("["+operator+"]")[0]);
        Double num2 = Double.parseDouble(in.split("["+operator+"]")[1]);

        return  Double.toString(
            switch(operator) {
                case "+" -> num1 + num2;
                case "-" -> num1 - num2;
                case "/" -> num1 / num2;
                case "*" -> num1 * num2;
                default -> 0;
            }
        );
    }
}