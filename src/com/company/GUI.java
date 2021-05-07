package com.company;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame{

    private final JPanel windowContent;
    private final JTextField displayField;
    private JButton[] buttons;
    private String[] buttonNames;

    private final GridBagLayout gridBagLayout;
    private final GridBagConstraints constraints;

    public static void main(String[] args) {
        GUI gui = new GUI();
    }

    GUI() {
        super("TBCalc");

        windowContent = new JPanel();
        gridBagLayout = new GridBagLayout();
        windowContent.setLayout(gridBagLayout);

        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        displayField = new JTextField("0");
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(displayField, 0, 0, 5, 1);

        String[] buttonNames = {"MR", "MC", "M+", "DEL", "C",
                "(-1)", "^0.5", "/", "*", "-", "+", "=", ".",
                "9", "8", "7", "6", "5", "4", "3", "2", "1", "0"};

        buttons = new JButton[buttonNames.length];
        for (int i = 0; i < buttonNames.length; i++) {
            buttons[i] = new JButton(buttonNames[i]);
        }

        addComponent(buttons[0], 0, 1, 1, 1);
        addComponent(buttons[1], 1, 1, 1, 1);
        addComponent(buttons[2], 2, 1, 1, 1);
        addComponent(buttons[3], 3, 1, 1, 1);
        addComponent(buttons[4], 4, 1, 1, 1);

        addComponent(buttons[15], 0, 2, 1, 1);
        addComponent(buttons[14], 1, 2, 1, 1);
        addComponent(buttons[13], 2, 2, 1, 1);
        addComponent(buttons[5], 3, 2, 1, 1);
        addComponent(buttons[6], 4, 2, 1, 1);

        addComponent(buttons[18], 0, 3, 1, 1);
        addComponent(buttons[17], 1, 3, 1, 1);
        addComponent(buttons[16], 2, 3, 1, 1);
        addComponent(buttons[7], 3, 3, 1, 1);
        addComponent(buttons[8], 4, 3, 1, 1);

        addComponent(buttons[21], 0, 4, 1, 1);
        addComponent(buttons[20], 1, 4, 1, 1);
        addComponent(buttons[19], 2, 4, 1, 1);
        addComponent(buttons[9], 3, 4, 1, 1);
        addComponent(buttons[11], 4, 4, 1, 2);

        addComponent(buttons[22], 0, 5, 2, 1);
        addComponent(buttons[12], 2, 5, 1, 1);
        addComponent(buttons[10], 3, 5, 1, 1);

        setContentPane(windowContent);
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        Engine engine = new Engine(this);
        for (int i = 0; i < buttonNames.length; i++) {
            buttons[i].addActionListener(engine);
        }
    }

    private void addComponent(Component windowElement, int x, int y, int width, int height) {
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        gridBagLayout.setConstraints(windowElement, constraints);
        windowContent.add(windowElement);
    }

    public String getDisplayValue() {
        return displayField.getText();
    }

    public void setDisplayValue(String value) {
        displayField.setText(value);
    }

    public String[] getButtonNames() {
        return buttonNames;
    }
}
