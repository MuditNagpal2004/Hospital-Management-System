package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.Date;

public class patient_discharge extends JFrame {

    patient_discharge() {

        // Main panel with layout
        JPanel panel = new JPanel(null);
        panel.setBounds(5, 5, 790, 390);
        panel.setBackground(new Color(90, 156, 163));
        add(panel);

        // Title Label
        JLabel label = new JLabel("PATIENT CHECK-OUT");
        label.setBounds(260, 15, 350, 30);
        label.setFont(new Font("Tahoma", Font.BOLD, 24));
        label.setForeground(Color.WHITE);
        panel.add(label);

        JSeparator sep = new JSeparator();
        sep.setBounds(30, 55, 720, 10);
        panel.add(sep);

        // Patient Number
        JLabel label1 = new JLabel("Patient Number:");
        label1.setBounds(80, 80, 150, 25);
        label1.setFont(new Font("Tahoma", Font.BOLD, 16));
        label1.setForeground(Color.WHITE);
        panel.add(label1);

        Choice choice = new Choice();
        choice.setBounds(250, 80, 200, 25);
        panel.add(choice);

        // Fill choices
        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM patient_info");
            while (resultSet.next()) {
                choice.addItem(resultSet.getString("Number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Room Number
        JLabel label2 = new JLabel("Room Number:");
        label2.setBounds(80, 130, 150, 25);
        label2.setFont(new Font("Tahoma", Font.BOLD, 16));
        label2.setForeground(Color.WHITE);
        panel.add(label2);

        JLabel RNo = new JLabel();
        RNo.setBounds(250, 130, 200, 25);
        RNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        RNo.setForeground(Color.WHITE);
        panel.add(RNo);

        // In Time
        JLabel label4 = new JLabel("In Time:");
        label4.setBounds(80, 180, 150, 25);
        label4.setFont(new Font("Tahoma", Font.BOLD, 16));
        label4.setForeground(Color.WHITE);
        panel.add(label4);

        JLabel InTime = new JLabel();
        InTime.setBounds(250, 180, 350, 25);
        InTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
        InTime.setForeground(Color.WHITE);
        panel.add(InTime);

        // Out Time
        JLabel label5 = new JLabel("Out Time:");
        label5.setBounds(80, 230, 150, 25);
        label5.setFont(new Font("Tahoma", Font.BOLD, 16));
        label5.setForeground(Color.WHITE);
        panel.add(label5);

        Date date = new Date();
        JLabel OutTime = new JLabel("" + date);
        OutTime.setBounds(250, 230, 350, 25);
        OutTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
        OutTime.setForeground(Color.WHITE);
        panel.add(OutTime);

        // Buttons
        JButton btnDischarge = createButton("Discharge");
        btnDischarge.setBounds(100, 300, 140, 40);
        panel.add(btnDischarge);

        JButton btnCheck = createButton("Check");
        btnCheck.setBounds(300, 300, 140, 40);
        panel.add(btnCheck);

        JButton btnBack = createButton("Back");
        btnBack.setBounds(500, 300, 140, 40);
        panel.add(btnBack);

        // Action Listeners
        btnCheck.addActionListener(e -> {
            Conn c = new Conn();
            try {
                String patientNumber = choice.getSelectedItem();
                ResultSet rs = c.statement.executeQuery("SELECT * FROM patient_info WHERE Number='" + patientNumber + "'");
                if (rs.next()) {
                    RNo.setText(rs.getString("Room_Number"));
                    InTime.setText(rs.getString("Time"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnDischarge.addActionListener(e -> {
            Conn conn = new Conn();
            try {
                String patientNumber = choice.getSelectedItem();
                String room = RNo.getText();

                conn.statement.executeUpdate("DELETE FROM patient_info WHERE Number = '" + patientNumber + "'");
                conn.statement.executeUpdate("UPDATE Room SET Availability = 'Available' WHERE room_no = '" + room + "'");

                JOptionPane.showMessageDialog(null, "Patient Discharged Successfully!");
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnBack.addActionListener(e -> dispose());

        // Frame settings
        setSize(800, 420);
        setLayout(null);
        setLocation(400, 250);
        setTitle("Patient Discharge");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // 🔘 Styled Button Generator
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(0, 102, 153));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Tahoma", Font.BOLD, 15));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(0, 80, 120));
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(0, 102, 153));
            }
        });

        return button;
    }

    public static void main(String[] args) {
        new patient_discharge();
    }
}
