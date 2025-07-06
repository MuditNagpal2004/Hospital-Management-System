package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class update_patient_details extends JFrame {

    update_patient_details() {

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 940, 490);
        panel.setBackground(new Color(90, 156, 169));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/updated.png"));
        Image image = imageIcon.getImage().getScaledInstance(300, 250, Image.SCALE_DEFAULT);
        JLabel label = new JLabel(new ImageIcon(image));
        label.setBounds(500, 60, 300, 300);
        panel.add(label);

        JLabel label1 = new JLabel("Update Patient Details");
        label1.setBounds(124, 11, 260, 25);
        label1.setFont(new Font("Tahoma", Font.BOLD, 18));
        label1.setForeground(Color.white);
        panel.add(label1);

        JLabel label2 = new JLabel("Name :");
        label2.setBounds(25, 88, 100, 14);
        label2.setFont(new Font("Tahoma", Font.BOLD, 14));
        label2.setForeground(Color.white);
        panel.add(label2);

        Choice choice = new Choice();
        choice.setBounds(248, 85, 140, 25);
        panel.add(choice);

        try {
            Conn c = new Conn();
            ResultSet rs = c.statement.executeQuery("SELECT * FROM patient_info");
            while (rs.next()) {
                choice.add(rs.getString("Name"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JLabel label3 = new JLabel("Room Number :");
        label3.setBounds(25, 129, 150, 14);
        label3.setFont(new Font("Tahoma", Font.BOLD, 14));
        label3.setForeground(Color.white);
        panel.add(label3);

        JTextField textFieldR = new JTextField();
        textFieldR.setBounds(248, 129, 140, 20);
        panel.add(textFieldR);

        JLabel label4 = new JLabel("In-Time :");
        label4.setBounds(25, 174, 100, 14);
        label4.setFont(new Font("Tahoma", Font.BOLD, 14));
        label4.setForeground(Color.white);
        panel.add(label4);

        JTextField textFieldINTTime = new JTextField();
        textFieldINTTime.setBounds(248, 174, 140, 20);
        panel.add(textFieldINTTime);

        JLabel label5 = new JLabel("Amount Paid (Rs.) :");
        label5.setBounds(25, 216, 150, 20);
        label5.setFont(new Font("Tahoma", Font.BOLD, 14));
        label5.setForeground(Color.white);
        panel.add(label5);

        JTextField textFieldAmount = new JTextField();
        textFieldAmount.setBounds(248, 216, 140, 20);
        panel.add(textFieldAmount);

        JLabel label6 = new JLabel("Pending Amount (Rs.) :");
        label6.setBounds(25, 261, 200, 20);
        label6.setFont(new Font("Tahoma", Font.BOLD, 14));
        label6.setForeground(Color.white);
        panel.add(label6);

        JTextField textFieldPAmount = new JTextField();
        textFieldPAmount.setBounds(248, 261, 140, 20);
        panel.add(textFieldPAmount);

        JButton buttonCheck = new JButton("CHECK");
        buttonCheck.setBounds(281, 378, 89, 25);
        buttonCheck.setForeground(Color.white);
        buttonCheck.setBackground(Color.BLACK);
        panel.add(buttonCheck);

        buttonCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = choice.getSelectedItem();
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.statement.executeQuery("SELECT * FROM patient_info WHERE Name='" + id + "'");
                    if (rs.next()) {
                        textFieldR.setText(rs.getString("Room_Number"));
                        textFieldINTTime.setText(rs.getString("Time"));
                        textFieldAmount.setText(rs.getString("Deposit"));
                    }

                    ResultSet roomRs = c.statement.executeQuery("SELECT * FROM Room WHERE room_no='" + textFieldR.getText() + "'");
                    if (roomRs.next()) {
                        int price = Integer.parseInt(roomRs.getString("Price"));
                        int deposit = Integer.parseInt(textFieldAmount.getText());
                        int pending = price - deposit;
                        textFieldPAmount.setText("" + pending);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton buttonUpdate = new JButton("UPDATE");
        buttonUpdate.setBounds(56, 378, 89, 25);
        buttonUpdate.setForeground(Color.white);
        buttonUpdate.setBackground(Color.BLACK);
        panel.add(buttonUpdate);

        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Conn c = new Conn();
                    String q = choice.getSelectedItem();
                    String room = textFieldR.getText();
                    String time = textFieldINTTime.getText();
                    String deposit = textFieldAmount.getText();
                    String query = "UPDATE patient_info SET Room_Number='" + room + "', Time='" + time + "', Deposit='" + deposit + "' WHERE Name='" + q + "'";
                    c.statement.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Updated Successfully!");
                    setVisible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton buttonBack = new JButton("BACK");
        buttonBack.setBounds(160, 378, 89, 25);
        buttonBack.setForeground(Color.white);
        buttonBack.setBackground(Color.BLACK);
        panel.add(buttonBack);

        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setTitle("Update Patient Details");
        setSize(950, 500);
        setLayout(null);
        setLocation(400, 250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new update_patient_details();
    }
}
