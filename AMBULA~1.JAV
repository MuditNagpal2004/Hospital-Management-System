package hospital.management.system;
import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
public class Ambulance extends JFrame {
    Ambulance() {
        setTitle("Hospital Ambulance Information");
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 590);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);
        // Heading
        JLabel heading = new JLabel("Hospital Ambulance Information");
        heading.setBounds(250, 10, 500, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        heading.setForeground(Color.WHITE);
        panel.add(heading);
        // Column labels
        JLabel label1 = new JLabel("Name");
        label1.setBounds(31, 50, 100, 20);
        label1.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label1);
        JLabel label2 = new JLabel("Gender");
        label2.setBounds(190, 50, 100, 20);
        label2.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label2);
        JLabel label3 = new JLabel("Car Name");
        label3.setBounds(380, 50, 100, 20);
        label3.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label3);
        JLabel label4 = new JLabel("Phone Number");
        label4.setBounds(530, 50, 180, 20);
        label4.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label4);
        JLabel label5 = new JLabel("Location");
        label5.setBounds(720, 50, 100, 20);
        label5.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label5);
        // Table
        JTable table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 13));
        table.setRowHeight(25);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only one row selection
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 80, 850, 400);
        panel.add(scrollPane);
        // Load data from DB
        try {
            Conn c = new Conn();
            String q = "SELECT * FROM Ambulance";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading ambulance data.");
        }
        // Book Button
        JButton bookButton = new JButton("BOOK");
        bookButton.setBounds(250, 500, 120, 35);
        bookButton.setBackground(new Color(34, 139, 34)); // Green color for booking
        bookButton.setForeground(Color.WHITE);
        bookButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(bookButton);

        bookButton.addActionListener((ActionEvent e) -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please select an ambulance to book.");
            } else {
                // Extract data from selected row
                String name = table.getValueAt(selectedRow, 0).toString();
                String carName = table.getValueAt(selectedRow, 2).toString();
                String phoneNumber = table.getValueAt(selectedRow, 3).toString();
                // Confirm booking
                String bookingMessage = String.format(
                        "Ambulance booked successfully!\n" +
                                "Details:\nName: %s\nCar Name: %s\nPhone Number: %s",
                        name, carName, phoneNumber
                );
                JOptionPane.showMessageDialog(null, bookingMessage, "Booking Confirmation", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        // Back Button
        JButton backButton = new JButton("BACK");
        backButton.setBounds(390, 500, 120, 35);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(backButton);
        backButton.addActionListener((ActionEvent e) -> setVisible(false));
        // Frame settings
        setSize(900, 600);
        setLayout(null);
        setLocation(300, 200);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Ambulance();
    }
}