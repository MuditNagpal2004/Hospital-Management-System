package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

// Super simple form to update employee details for beginners
public class UPDATE_EMPLOYEE extends JFrame implements ActionListener {
    // Text fields for employee info
    private JTextField aadharField, nameField, ageField, phoneField, salaryField, gmailField, newAadharField;
    // Buttons for actions
    private JButton searchButton, updateButton, backButton;

    public UPDATE_EMPLOYEE() {
        // Set up the window
        setTitle("Update Employee");
        setSize(350, 400); // Very small size
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Main panel with plain background
        JPanel panel = new JPanel();
        panel.setBackground(new Color(109, 164, 170)); // Same color as Employee_info
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        add(panel);

        // Title
        JLabel title = new JLabel("Update Employee");
        title.setFont(new Font("Tahoma", Font.BOLD, 16));
        panel.add(title);

        // Spacer (empty space for line break)
        panel.add(createSpacer(300, 10));

        // Search Aadhar
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

        // Name
        panel.add(new JLabel("Name:"));
        nameField = new JTextField(10);
        panel.add(nameField);

        // Age
        panel.add(new JLabel("Age:"));
        ageField = new JTextField(10);
        panel.add(ageField);

        // Phone
        panel.add(new JLabel("Phone:"));
        phoneField = new JTextField(10);
        panel.add(phoneField);

        // Spacer
        panel.add(createSpacer(300, 10));

        // Salary
        panel.add(new JLabel("Salary:"));
        salaryField = new JTextField(10);
        panel.add(salaryField);

        // Gmail
        panel.add(new JLabel("Gmail:"));
        gmailField = new JTextField(10);
        panel.add(gmailField);

        // New Aadhar
        panel.add(new JLabel("New Aadhar:"));
        newAadharField = new JTextField(10);
        panel.add(newAadharField);

        // Spacer
        panel.add(createSpacer(300, 10));

        // Buttons
        updateButton = new JButton("Update");
        updateButton.setBackground(new Color(0, 120, 215));
        updateButton.setForeground(Color.WHITE);
        updateButton.addActionListener(this);
        updateButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                updateButton.setBackground(new Color(0, 100, 200));
            }
            public void mouseExited(MouseEvent e) {
                updateButton.setBackground(new Color(0, 120, 215));
            }
        });
        panel.add(updateButton);

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
                String query = "SELECT * FROM EMP_INFO WHERE aadhar_number = ?";
                PreparedStatement ps = c.connection.prepareStatement(query);
                ps.setString(1, aadhar);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    nameField.setText(rs.getString("name"));
                    ageField.setText(rs.getString("age"));
                    phoneField.setText(rs.getString("phone_number"));
                    salaryField.setText(rs.getString("salary"));
                    gmailField.setText(rs.getString("gmail"));
                    newAadharField.setText(rs.getString("aadhar_number"));
                } else {
                    JOptionPane.showMessageDialog(this, "Employee not found!");
                    clearFields();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database error!");
            } finally {
                c.close();
            }
        }
        // Update button: Save changes
        else if (e.getSource() == updateButton) {
            String name = nameField.getText().trim();
            String age = ageField.getText().trim();
            String phone = phoneField.getText().trim();
            String salary = salaryField.getText().trim();
            String gmail = gmailField.getText().trim();
            String newAadhar = newAadharField.getText().trim();
            String searchAadhar = aadharField.getText().trim();

            // Check for empty fields
            if (name.isEmpty() || age.isEmpty() || phone.isEmpty() || salary.isEmpty() || gmail.isEmpty() || newAadhar.isEmpty() || searchAadhar.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill all fields!");
                return;
            }

            Conn c = new Conn();
            try {
                String query = "UPDATE EMP_INFO SET name = ?, age = ?, phone_number = ?, salary = ?, gmail = ?, aadhar_number = ? WHERE aadhar_number = ?";
                PreparedStatement ps = c.connection.prepareStatement(query);
                ps.setString(1, name);
                ps.setString(2, age);
                ps.setString(3, phone);
                ps.setString(4, salary);
                ps.setString(5, gmail);
                ps.setString(6, newAadhar);
                ps.setString(7, searchAadhar);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Employee updated!");
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

    // Clear all fields
    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        phoneField.setText("");
        salaryField.setText("");
        gmailField.setText("");
        newAadharField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UPDATE_EMPLOYEE());
    }
}