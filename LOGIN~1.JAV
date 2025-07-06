package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JTextField textField;
    JPasswordField passwordField;
    JButton loginButton, cancelButton;
    JLabel backgroundLabel;

    Login() {
        setTitle("Hospital Management System - Login");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // ==== Load Background Image Safely ====
        URL bgURL = ClassLoader.getSystemResource("Icons/lifetime.png");
        if (bgURL == null) {
            JOptionPane.showMessageDialog(null, "Background image not found!");
            System.exit(1);
        }
        ImageIcon bgIcon = new ImageIcon(bgURL);
        Image bgImg = bgIcon.getImage().getScaledInstance(900, 500, Image.SCALE_SMOOTH);
        backgroundLabel = new JLabel(new ImageIcon(bgImg));
        backgroundLabel.setBounds(0, 0, 900, 500);
        add(backgroundLabel);

        // ==== Semi-transparent Login Panel ====
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBounds(480, 60, 360, 380);
        loginPanel.setBackground(new Color(255, 255, 255, 220)); // white with transparency
        loginPanel.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1, true));
        backgroundLabel.add(loginPanel);

        JLabel title = new JLabel("Admin Login");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(0, 102, 102));
        title.setBounds(110, 30, 200, 30);
        loginPanel.add(title);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(40, 90, 100, 20);
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        loginPanel.add(userLabel);

        textField = new JTextField();
        textField.setBounds(40, 115, 280, 30);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        loginPanel.add(textField);

        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(40, 160, 100, 20);
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        loginPanel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(40, 185, 280, 30);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        loginPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(40, 240, 130, 40);
        loginButton.setBackground(new Color(0, 123, 138));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        loginButton.addActionListener(this);
        loginPanel.add(loginButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(190, 240, 130, 40);
        cancelButton.setBackground(new Color(220, 53, 69));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cancelButton.setFocusPainted(false);
        cancelButton.setBorder(BorderFactory.createEmptyBorder());
        cancelButton.addActionListener(this);
        loginPanel.add(cancelButton);

        // ==== Hospital Side Image (safe loading) ====
        URL hospitalURL = ClassLoader.getSystemResource("Icons/Hospitals.png");
        if (hospitalURL == null) {
            JOptionPane.showMessageDialog(null, "Hospital image not found!");
            System.exit(1);
        }
        ImageIcon hospitalIcon = new ImageIcon(hospitalURL);
        Image hospitalImg = hospitalIcon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(hospitalImg));
        imageLabel.setBounds(20, 50, 400, 400);
        backgroundLabel.add(imageLabel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String user = textField.getText().trim();
            String pass = new String(passwordField.getPassword()).trim();
            try {
                Conn c = new Conn();
                PreparedStatement pst = c.connection.prepareStatement("SELECT * FROM Login WHERE ID = ? AND PW = ?");
                pst.setString(1, user);
                pst.setString(2, pass);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    new Reception();
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
            }
        } else {
            System.exit(0);
        }
    }
}
