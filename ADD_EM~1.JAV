package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ADD_EMPLOYEE extends JFrame implements ActionListener {
    private JTextField textName, textAge, textPhone, textSalary, textGmail, textAadhar;
    private JButton addButton, backButton;

    public ADD_EMPLOYEE() {
        setTitle("Add New Employee");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title Panel
        JLabel title = new JLabel("Add New Employee", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(0, 51, 102));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        textName = addField(formPanel, "Name:");
        textAge = addField(formPanel, "Age:");
        textPhone = addField(formPanel, "Phone:");
        textSalary = addField(formPanel, "Salary:");
        textGmail = addField(formPanel, "Gmail:");
        textAadhar = addField(formPanel, "Aadhar:");

        add(formPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add Employee");
        backButton = new JButton("Back");
        styleButton(addButton);
        styleButton(backButton);
        buttonPanel.add(addButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(this);
        backButton.addActionListener(this);

        setVisible(true);
    }

    private JTextField addField(JPanel panel, String labelText) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Tahoma", Font.PLAIN, 14));
        JTextField textField = new JTextField();
        panel.add(label);
        panel.add(textField);
        return textField;
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(0, 102, 153));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Tahoma", Font.BOLD, 14));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String name = textName.getText().trim();
            String age = textAge.getText().trim();
            String phone = textPhone.getText().trim();
            String salary = textSalary.getText().trim();
            String gmail = textGmail.getText().trim();
            String aadhar = textAadhar.getText().trim();

            if (name.isEmpty() || age.isEmpty() || phone.isEmpty() || salary.isEmpty() || gmail.isEmpty() || aadhar.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.", "Missing Info", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                Integer.parseInt(age);
                Double.parseDouble(salary);

                Conn c = new Conn();
                String query = "INSERT INTO EMP_INFO (name, age, phone_number, salary, gmail, aadhar_number) VALUES ('"
                        + name + "', '" + age + "', '" + phone + "', '" + salary + "', '" + gmail + "', '" + aadhar + "')";
                c.statement.executeUpdate(query);
                c.close();

                JOptionPane.showMessageDialog(this, "Employee added successfully!");
                setVisible(false);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Age and Salary must be numbers.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == backButton) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ADD_EMPLOYEE::new);
    }
}
