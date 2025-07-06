package hospital.management.system;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.*;

public class Room extends JFrame {
    JTable table;

    Room() {
        setTitle("Room Information");
        setSize(820, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(230, 245, 255));

        JLabel heading = new JLabel("Room Information");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 24));
        heading.setBounds(260, 20, 300, 30);
        heading.setForeground(new Color(0, 102, 204));
        add(heading);

        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(26);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 15));
        header.setBackground(new Color(0, 102, 204));
        header.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(40, 70, 720, 380);
        add(scrollPane);

        try {
            Conn c = new Conn();
            ResultSet rs = c.statement.executeQuery("SELECT * FROM room");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading room data");
        }

        JButton back = new JButton("BACK");
        back.setBounds(350, 470, 120, 35);
        back.setFont(new Font("Segoe UI", Font.BOLD, 15));
        back.setBackground(new Color(0, 102, 204));
        back.setForeground(Color.WHITE);
        back.setFocusPainted(false);
        add(back);

        back.addActionListener(e -> setVisible(false));
        setVisible(true);
    }
    public static void main(String[] args) {
        new Room();
    }
}