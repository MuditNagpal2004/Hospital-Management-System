package hospital.management.system;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ALL_Patient_Info extends JFrame {
    public ALL_Patient_Info() {
        setTitle("All Patient Information");
        setSize(1000, 600);
        setLocation(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(90, 156, 163));
        add(panel);

        JTable table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 960, 450);
        panel.add(scrollPane);

        try {
            Conn c = new Conn();
            String query = "SELECT * FROM patient_info";
            ResultSet resultSet = c.statement.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading patient data.");
        }

        JButton backButton = new JButton("BACK");
        backButton.setBounds(400, 480, 150, 40);
        backButton.setForeground(Color.white);
        backButton.setBackground(Color.BLACK);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        panel.add(backButton);

        backButton.addActionListener(e -> setVisible(false));

        setVisible(true);
    }

    public static void main(String[] args) {
        new ALL_Patient_Info();
    }
}