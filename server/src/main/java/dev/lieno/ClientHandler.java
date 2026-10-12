package dev.lieno;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{

    private Socket s;

    public ClientHandler(Socket socket)
        {
            this.s = socket;
        }

    @Override
    public void run() {
        // Socket input output
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream(), true);

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
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private String calc(String in, String operator) {
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
