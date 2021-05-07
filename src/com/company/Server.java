package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
    private static final int port = 3000;
    public static void main(String[] args) {
        Socket clientSocket = null;
        ServerSocket serverSocket = null;

        BufferedReader inbound = null;
        OutputStream outbound = null;

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started with port: " + port);

            while (true) {
                System.out.println("Waiting connection...");
                clientSocket = serverSocket.accept();
                System.out.println("Connection established. Data processing...");

                inbound = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                outbound = clientSocket.getOutputStream();

                String input = inbound.readLine();
                if (input.equals("-stop")) {
                    outbound.write(("\n" + "Server was stopped!" + "\n").getBytes(StandardCharsets.UTF_8));
                    System.out.println("Server was stopped!");
                    break;
                }

                double leftNumber;
                double rightNumber;
                double result = 0;
                try {
                    for (int i = 0; i < input.length(); i++) {
                        if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*' || input.charAt(i) == '/') {
                            leftNumber = Double.parseDouble(input.substring(0, i));
                            rightNumber = Double.parseDouble(input.substring(i + 1));

                            if (input.charAt(i) == '+') {
                                result = leftNumber + rightNumber;
                            } else if (input.charAt(i) == '-') {
                                result = leftNumber - rightNumber;
                            } else if (input.charAt(i) == '*') {
                                result = leftNumber * rightNumber;
                            } else if (input.charAt(i) == '/') {
                                if (rightNumber == 0) {
                                    throw new ArithmeticException("Division by zero!");
                                }
                                result = leftNumber / rightNumber;
                            }
                        }
                    }
                    outbound.write(("\n" + String.valueOf(result) + "\n").getBytes(StandardCharsets.UTF_8));
                } catch (Exception e) {
                    outbound.write(0);
                }
                System.out.println("End of the current session.");
            }
        } catch (IOException ioe) {
            System.out.println("Server starting error: " + ioe.getMessage());
        } finally {
            try {
                if (inbound != null) inbound.close();
                if (outbound != null) outbound.close();
                if (clientSocket != null) clientSocket.close();
                if (serverSocket != null) serverSocket.close();
            } catch (Exception ioe) {
                System.out.println("Cannot close stream: " + ioe.getMessage());
            }
        }
    }
}
