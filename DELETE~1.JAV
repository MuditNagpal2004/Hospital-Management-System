package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

// Super simple form to delete an employee for beginners
public class DELETE_EMPLOYEE extends JFrame implements ActionListener {
    // Text field for Aadhar Number
    private JTextField aadharField;
    // Label to show employee name
    private JLabel nameLabel;
    // Buttons for actions
    private JButton searchButton, deleteButton, backButton;

    public DELETE_EMPLOYEE() {
        // Set up the window
        setTitle("Delete Employee");
        setSize(350, 250); // Very small size
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Main panel with plain background
        JPanel panel = new JPanel();
        panel.setBackground(new Color(109, 164, 170)); // Blue from Employee_info
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        add(panel);

        // Title
        JLabel title = new JLabel("Delete Employee");
        title.setFont(new Font("Tahoma", Font.BOLD, 16));
        panel.add(title);

        // Spacer (empty space for line break)
        panel.add(createSpacer(300, 10));

        // Aadhar Number input
        panel.add(new JLabel("Aadhar:"));
        aadharField = new JTextField(10);
        panel.add(aadharField);

        searchButton = new JButton("Search");
        searchButton.setBackground(new Color(0, 120, 215));
        searchButton.setForeground(Color.WHITE);
        searchButton.addActionListener(this);
        searchButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                searchButton.setBackground(new Color(0, 100, 200)); // Hover color
            }
            public void mouseExited(MouseEvent e) {
                searchButton.setBackground(new Color(0, 120, 215)); // Normal color
            }
        });
        panel.add(searchButton);

        // Spacer
        panel.add(createSpacer(300, 10));

        // Employee name display
        panel.add(new JLabel("Name:"));
        nameLabel = new JLabel("Not found");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        panel.add(nameLabel);

        // Spacer
        panel.add(createSpacer(300, 10));

        // Buttons
        deleteButton = new JButton("Delete");
        deleteButton.setBackground(new Color(0, 120, 215));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(this);
        deleteButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                deleteButton.setBackground(new Color(0, 100, 200));
            }
            public void mouseExited(MouseEvent e) {
                deleteButton.setBackground(new Color(0, 120, 215));
            }
        });
        panel.add(deleteButton);

        backButton = new JButton("Back");
        backButton.setBackground(new Color(0, 120, 215));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        backButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                backButton.setBackground(new Color(0, 100, 200));
            }
            public void mouseExited(MouseEvent e) {
                backButton.setBackground(new Color(0, 120, 215));
            }
        });
        panel.add(backButton);

        setVisible(true);
    }

    // Helper method to add empty space (line break)
    private JPanel createSpacer(int width, int height) {
        JPanel spacer = new JPanel();
        spacer.setOpaque(false);
        spacer.setPreferredSize(new Dimension(width, height));
        return spacer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Search button: Find employee
        if (e.getSource() == searchButton) {
            String aadhar = aadharField.getText().trim();
            if (aadhar.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Aadhar!");
                return;
            }

            Conn c = new Conn();
            try {
                String query = "SELECT name FROM EMP_INFO WHERE aadhar_number = ?";
                PreparedStatement ps = c.connection.prepareStatement(query);
                ps.setString(1, aadhar);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    nameLabel.setText(rs.getString("name")); // Show employee name
                } else {
                    nameLabel.setText("Not found");
                    JOptionPane.showMessageDialog(this, "Employee not found!");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database error!");
            } finally {
                c.close();
            }
        }
        // Delete button: Remove employee
        else if (e.getSource() == deleteButton) {
            String aadhar = aadharField.getText().trim();
            if (aadhar.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Aadhar!");
                return;
            }
            if (nameLabel.getText().equals("Not found")) {
                JOptionPane.showMessageDialog(this, "Search for an employee first!");
                return;
            }

            Conn c = new Conn();
            try {
                String query = "DELETE FROM EMP_INFO WHERE aadhar_number = ?";
                PreparedStatement ps = c.connection.prepareStatement(query);
                ps.setString(1, aadhar);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Employee deleted!");
                setVisible(false);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database error!");
            } finally {
                c.close();
            }
        }
        // Back button: Close form
        else if (e.getSource() == backButton) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DELETE_EMPLOYEE());
    }
}