package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MedicalShop extends JFrame {

    JTextArea billArea;
    JComboBox medList;
    JTextField quantityField, nameField;
    Connection conn;

    MedicalShop() {
        setTitle("Medical Shop");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_system", "root", "12345");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database not connected: " + e.getMessage());
        }

        // Title
        JLabel title = new JLabel("Medical Shop Billing", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        nameField = new JTextField();
        medList = new JComboBox<>();
        quantityField = new JTextField();

        JButton buyButton = new JButton("Buy Medicine");

        inputPanel.add(new JLabel("Customer Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Select Medicine:"));
        inputPanel.add(medList);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityField);
        inputPanel.add(new JLabel(""));
        inputPanel.add(buyButton);

        add(inputPanel, BorderLayout.CENTER);

        // Bill Display
        billArea = new JTextArea(10, 30);
        billArea.setEditable(false);
        billArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(billArea), BorderLayout.SOUTH);

        // Load medicines into combo box
        loadMedicines();

        // Buy Button Action
        buyButton.addActionListener(e -> processPurchase());

        setVisible(true);
    }

    void loadMedicines() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM medicine_stock");
            while (rs.next()) {
                medList.addItem(rs.getString("name"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to load medicines: " + e.getMessage());
        }
    }

    void processPurchase() {
        String customer = nameField.getText().trim();
        String selectedMedicine = (String) medList.getSelectedItem();
        String qtyStr = quantityField.getText().trim();

        if (customer.isEmpty() || qtyStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter all fields.");
            return;
        }

        try {
            int qty = Integer.parseInt(qtyStr);
            if (qty <= 0) throw new NumberFormatException();

            PreparedStatement pst = conn.prepareStatement("SELECT quantity, price_per_unit FROM medicine_stock WHERE name=?");
            pst.setString(1, selectedMedicine);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int available = rs.getInt("quantity");
                double price = rs.getDouble("price_per_unit");

                if (qty > available) {
                    JOptionPane.showMessageDialog(this, "Only " + available + " units available.");
                } else {
                    double total = qty * price;

                    // Update quantity in database
                    int newQty = available - qty;
                    PreparedStatement update = conn.prepareStatement("UPDATE medicine_stock SET quantity=? WHERE name=?");
                    update.setInt(1, newQty);
                    update.setString(2, selectedMedicine);
                    update.executeUpdate();

                    billArea.setText("Customer: " + customer + "\n");
                    billArea.append("Medicine: " + selectedMedicine + "\n");
                    billArea.append("Quantity: " + qty + "\n");
                    billArea.append("Unit Price: ₹" + price + "\n");
                    billArea.append("Total: ₹" + total + "\n");
                    billArea.append("Purchase Successful!\n");

                    // Clear fields
                    quantityField.setText("");
                    nameField.setText("");
                }
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid quantity number.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load JDBC Driver
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "MySQL JDBC Driver not found.");
            return;
        }

        new MedicalShop();
    }
}
