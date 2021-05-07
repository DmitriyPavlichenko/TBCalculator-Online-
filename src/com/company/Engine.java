package com.company;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Engine implements ActionListener{
    private static final int port = 3000;
    private static final String hostname = "192.168.88.253";
    private static GUI parent;

    Engine(GUI parent){
        Engine.parent = parent;
    }

    public void actionPerformed(ActionEvent event) {
        JButton clickedButton = (JButton) event.getSource();

        switch (clickedButton.getText()) {
            case "MR", "MC", "M+", "DEL", "C", "(-1)", "^0.5" -> //TODO
                    JOptionPane.showConfirmDialog(null, "Beta functional is limited.",
                            "Sorry, you cannot do it...", JOptionPane.DEFAULT_OPTION);
            case "=" -> {
                String inputData = parent.getDisplayValue();
                try {
                    parent.setDisplayValue(Double.toString(calculate(inputData)));
                } catch (Exception e) {
                    parent.setDisplayValue(e.getMessage());
                }
            }
            default -> displayText(clickedButton);
        }

    }

    private void displayText(JButton clickedButton) {
        String displayFieldText = parent.getDisplayValue();
        String clickedLabel = clickedButton.getText();
        parent.setDisplayValue(displayFieldText + clickedLabel);
    }
    
    private double calculate(String input) {
        System.out.println("Connecting to " + hostname + ":" + port);
        try (Socket clientSocket = new Socket(hostname, port)) {
            System.out.println("Successfully connected!");

            try (OutputStream outbound = clientSocket.getOutputStream(); BufferedReader inbound =
                    new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                outbound.write((input + "\n").getBytes(StandardCharsets.UTF_8));

                String quote;
                while (true) {
                    quote = inbound.readLine();
                    if (quote == null) break;

                    if (quote.isEmpty()) continue;
                    System.out.println(quote);
                    return Double.parseDouble(quote);
                }
            } catch (IOException ioE) {
                System.out.println("Failed stream creating: " + ioE.getMessage());
            }

        } catch (Exception exc) {
            System.out.println("Error! " + exc.getMessage());
        }
        return 0;
    }
    
}
