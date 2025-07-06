package hospital.management.system;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SearchRoom extends JFrame {
    Choice statusChoice;
    JTable table;

    SearchRoom() {
        // Frame setup
        setTitle("Search Room");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Label for heading
        JLabel heading = new JLabel("Search Room by Availability");
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        heading.setBounds(200, 20, 300, 30);
        add(heading);

        // Label for status
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        statusLabel.setBounds(50, 80, 100, 25);
        add(statusLabel);

        // Dropdown to choose status
        statusChoice = new Choice();
        statusChoice.setBounds(220, 80, 120, 25);
        statusChoice.add("Available");
        statusChoice.add("Occupied");
        add(statusChoice);

        // Table and scroll pane
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 130, 580, 250);
        add(scrollPane);

        // Load all rooms initially
        loadRoomData("SELECT * FROM room");

        // Search button
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(200, 400, 100, 30);
        add(searchButton);
        searchButton.addActionListener(e -> {
            String status = statusChoice.getSelectedItem();
            loadRoomData("SELECT * FROM room WHERE Availability='" + status + "'");
        });

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(350, 400, 100, 30);
        add(backButton);
        backButton.addActionListener(e -> setVisible(false));

        setVisible(true);
    }

    // Method to load data into the table
    private void loadRoomData(String query) {
        try {
            Conn c = new Conn();
            ResultSet rs = c.statement.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading data");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SearchRoom();
    }
}
