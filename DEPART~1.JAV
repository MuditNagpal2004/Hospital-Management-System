package hospital.management.system;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Department extends JFrame {

    Department() {

        // Panel setup
        JPanel panel = new JPanel();
        panel.setBounds(10, 5, 690, 490);
        panel.setLayout(null);
        panel.setBackground(new Color(240, 248, 255)); // Alice Blue
        add(panel);

        // Heading Labels
        JLabel label1 = new JLabel("Department");
        label1.setBounds(145, 11, 105, 20);
        label1.setFont(new Font("Tahoma", Font.BOLD, 14));
        label1.setForeground(new Color(0, 102, 102)); // Dark Cyan
        panel.add(label1);

        JLabel label2 = new JLabel("Phone Number");
        label2.setBounds(431, 11, 150, 20);
        label2.setFont(new Font("Tahoma", Font.BOLD, 14));
        label2.setForeground(new Color(0, 102, 102)); // Dark Cyan
        panel.add(label2);

        // Table setup
        JTable table = new JTable();
        table.setBackground(new Color(224, 255, 255)); // Light Cyan
        table.setForeground(Color.DARK_GRAY);
        table.setFont(new Font("Tahoma", Font.PLAIN, 13));
        table.setRowHeight(25);

        // Scroll Pane for table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 40, 690, 350);
        panel.add(scrollPane);

        // Database fetch
        try {
            Conn c = new Conn();
            String query = "SELECT * FROM department";
            ResultSet resultSet = c.statement.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Back Button
        JButton button = new JButton("BACK");
        button.setBounds(400, 410, 100, 30);
        button.setBackground(new Color(0, 102, 102));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel.add(button);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        // Frame setup
        setSize(710, 510);
        setLayout(null);
        setLocation(350, 250);
        setTitle("Department Details");
        setVisible(true);
    }

    public static void main(String[] args) {
        new Department();
    }
}
