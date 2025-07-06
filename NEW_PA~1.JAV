package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class NEW_PATIENT extends JFrame implements ActionListener {
    private JComboBox<String> comboBox;
    private JTextField textFieldNumber, textName, textFieldDisease, textFieldDeposit;
    private JRadioButton r1, r2;
    private Choice c1;
    private JLabel date;
    private JButton b1, b2;

    public NEW_PATIENT() {
        setTitle("New Patient Registration");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 248, 255));

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, new Color(135, 206, 250), 0, getHeight(), new Color(176, 246, 250));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelName = new JLabel("New Patient Registration", SwingConstants.CENTER);
        labelName.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 24));
        labelName.setForeground(new Color(0, 51, 102));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        panel.add(labelName, gbc);

        // ID Type
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(new JLabel("ID Type:"), gbc);

        comboBox = new JComboBox<>(new String[]{"Aadhar Card", "Voter ID", "Driving License"});
        gbc.gridx = 1;
        panel.add(comboBox, gbc);

        // ID Number
        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(new JLabel("ID Number:"), gbc);

        textFieldNumber = new JTextField(15);
        gbc.gridx = 1;
        panel.add(textFieldNumber, gbc);

        // Name
        gbc.gridy = 3;
        gbc.gridx = 0;
        panel.add(new JLabel("Name:"), gbc);

        textName = new JTextField(15);
        gbc.gridx = 1;
        panel.add(textName, gbc);

        // Gender
        gbc.gridy = 4;
        gbc.gridx = 0;
        panel.add(new JLabel("Gender:"), gbc);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        genderPanel.setBackground(new Color(176, 246, 250));

        r1 = new JRadioButton("Male");
        r2 = new JRadioButton("Female");

        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);

        r1.setBackground(new Color(176, 246, 250));
        r2.setBackground(new Color(176, 246, 250));

        genderPanel.add(r1);
        genderPanel.add(r2);

        gbc.gridx = 1;
        panel.add(genderPanel, gbc);

        // Disease
        gbc.gridy = 5;
        gbc.gridx = 0;
        panel.add(new JLabel("Disease:"), gbc);

        textFieldDisease = new JTextField(15);
        gbc.gridx = 1;
        panel.add(textFieldDisease, gbc);

        // Room
        gbc.gridy = 6;
        gbc.gridx = 0;
        panel.add(new JLabel("Room:"), gbc);

        c1 = new Choice();
        try {
            Conn c = new Conn();
            ResultSet rs = c.statement.executeQuery("SELECT * FROM Room WHERE Availability = 'Available'");
            while (rs.next()) {
                c1.add(rs.getString("room_no"));
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        gbc.gridx = 1;
        panel.add(c1, gbc);

        // Date
        gbc.gridy = 7;
        gbc.gridx = 0;
        panel.add(new JLabel("Admission Date:"), gbc);

        Date d = new Date();
        date = new JLabel("" + d);
        gbc.gridx = 1;
        panel.add(date, gbc);

        // Deposit
        gbc.gridy = 8;
        gbc.gridx = 0;
        panel.add(new JLabel("Deposit Amount:"), gbc);

        textFieldDeposit = new JTextField(15);
        gbc.gridx = 1;
        panel.add(textFieldDeposit, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);

        class RoundedButton extends JButton {
            public RoundedButton(String text) {
                super(text);
                setContentAreaFilled(false);
                setFocusPainted(false);
                setBorderPainted(false);
                setOpaque(false);
                setFont(new Font("Arial", Font.BOLD, 14));
                setForeground(Color.WHITE);
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color bg = getModel().isRollover() ? new Color(0, 100, 200) : new Color(25, 118, 210);
                g2.setPaint(bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.setColor(new Color(0, 0, 0, 50));
                g2.drawRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }
        }

        b1 = new RoundedButton("ADD PATIENT");
        b1.setPreferredSize(new Dimension(150, 40));
        b1.addActionListener(this);
        buttonPanel.add(b1);

        b2 = new RoundedButton("BACK");
        b2.setPreferredSize(new Dimension(150, 40));
        b2.addActionListener(this);
        buttonPanel.add(b2);

        gbc.gridy = 9;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        // Image Panel
        JPanel imagePanel = new JPanel();
        imagePanel.setOpaque(false);
        imagePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("Icons/patient.png"));
        Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        imagePanel.add(imageLabel);

        add(imagePanel, BorderLayout.EAST);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            Conn c = new Conn();
            String gender = r1.isSelected() ? "Male" : (r2.isSelected() ? "Female" : null);

            String idType = (String) comboBox.getSelectedItem();
            String idNumber = textFieldNumber.getText().trim();
            String name = textName.getText().trim();
            String disease = textFieldDisease.getText().trim();
            String room = c1.getSelectedItem();
            String dateStr = date.getText();
            String deposit = textFieldDeposit.getText().trim();

            if (idNumber.isEmpty() || name.isEmpty() || gender == null || disease.isEmpty() || deposit.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields", "Missing Info", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                Double.parseDouble(deposit);
                String query1 = "INSERT INTO patient_info VALUES('" + idType + "','" + idNumber + "','" + name + "','" + gender + "','" + disease + "','" + room + "','" + dateStr + "','" + deposit + "')";
                String query2 = "UPDATE Room SET Availability = 'Occupied' WHERE room_no = '" + room + "'";
                c.statement.executeUpdate(query1);
                c.statement.executeUpdate(query2);
                JOptionPane.showMessageDialog(this, "Patient added successfully");
                setVisible(false);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Deposit must be a number", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            } finally {
                c.close();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NEW_PATIENT::new);
    }
}
