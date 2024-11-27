package com.example;

import javax.swing.*;
import java.awt.*;

public class MySwingApp extends JFrame {
    public MySwingApp() {

        setTitle("Swing приложение");

        JPanel panel = new JPanel();
        panel.setName("mainPanel");

        JButton button = new JButton("Нажми меня");
        button.setName("myButton");
        panel.add(button);

        JTextField textField = new JTextField(20);
        textField.setName("myTextField");
        panel.add(textField);

        setContentPane(panel);

        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel.revalidate();
        panel.repaint();
        this.revalidate();
        this.repaint();
    }

    public static void main(String[] args) {

        System.setProperty("file.encoding", "UTF-8");

        UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 14));
        UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 14));
        UIManager.put("TextField.font", new Font("Arial", Font.PLAIN, 14));

        SwingUtilities.invokeLater(() -> {
            MySwingApp app = new MySwingApp();
            app.setVisible(true);

            if (app.isDisplayable()) {
                System.out.println("[LOG] Application is displayable.");
            } else {
                System.err.println("[ERROR] Application is not displayable.");
            }
        });
    }
}
