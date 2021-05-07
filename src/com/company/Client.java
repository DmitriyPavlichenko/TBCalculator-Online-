package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    private static final int port = 3000;
    private static final String hostname = "192.168.88.253";
    public static void main(String[] args) {
        System.out.println("Connecting to " + hostname + ":" + port);
        try (Socket clientSocket = new Socket(hostname, port)) {
            System.out.println("Successfully connected!");

            try (OutputStream outbound = clientSocket.getOutputStream(); BufferedReader inbound =
                    new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                outbound.write(((new Scanner(System.in).nextLine()) + "\n").getBytes(StandardCharsets.UTF_8));

                String quote;
                while (true) {
                    quote = inbound.readLine();
                    if (quote == null) break;

                    if (quote.isEmpty()) continue;
                    if (quote.equals("FlowEnd")) break;

                    System.out.println(quote);
                }
            } catch (IOException ioE) {
                System.out.println("Failed stream creating: " + ioE.getMessage());
            }

        } catch (Exception exc) {
            System.out.println("Error! " + exc.getMessage());
        }
        new Scanner(System.in).nextLine();
    }
}
