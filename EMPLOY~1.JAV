package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

public class Employee_info extends JFrame {
    Employee_info() {
        setTitle("Employee Information");
        setSize(1000, 600);
        setLocation(350, 230);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(null);
        panel.setBounds(5, 5, 990, 590);
        panel.setBackground(new Color(109, 164, 170));
        add(panel);

        JTable table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setGridColor(new Color(200, 200, 200));

        // Table header styling
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(0, 102, 153));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Tahoma", Font.BOLD, 15));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 40, 950, 400);
        panel.add(scrollPane);

        try {
            Conn c = new Conn();
            String query = "SELECT * FROM EMP_INFO";
            ResultSet resultSet = c.statement.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

            // Center align all cells
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Stylish BACK button
        JButton button = new JButton("BACK");
        button.setBounds(420, 470, 150, 40);
        button.setFont(new Font("Tahoma", Font.BOLD, 15));
        button.setBackground(new Color(0, 102, 153));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 80, 120));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 102, 153));
            }
        });

        button.addActionListener((ActionEvent e) -> setVisible(false));
        panel.add(button);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Employee_info();
    }
}
