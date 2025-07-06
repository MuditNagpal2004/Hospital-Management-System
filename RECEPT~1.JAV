package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.net.URL;

// Main dashboard for hospital reception with enhanced button colors
public class Reception extends JFrame {
    public Reception() {
        // Set up the window
        setTitle("Hospital Reception");
        setSize(1600, 900);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null); // Use null layout for exact positioning

        // Background image with transparency
        JLabel backgroundLabel = new JLabel(loadTransparentImage("Icons/Lifeline.jpg", 1600, 900, 0.3f));
        backgroundLabel.setBounds(0, 0, 1600, 900);
        setContentPane(backgroundLabel);

        // Header panel (blue, semi-transparent)
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setBounds(20, 20, 1960, 150);
        headerPanel.setBackground(new Color(0, 120, 215, 180));
        add(headerPanel);

        // Title
        JLabel titleLabel = new JLabel("Reception Dashboard", JLabel.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(400, 40, 800, 70);
        headerPanel.add(titleLabel);

        // Hospital logo
        JLabel logoLabel = new JLabel(loadImage("Icons/Hospital3.png", 250, 170));
        logoLabel.setBounds(30, -20, 250, 190);
        headerPanel.add(logoLabel);

        // Ambulance image
        JLabel ambulanceLabel = new JLabel(loadImage("Icons/amb1.png", 300, 120));
        ambulanceLabel.setBounds(1130, 15, 300, 120);
        headerPanel.add(ambulanceLabel);

        // Doctor image
        JLabel doctorLabel = new JLabel(loadImage("Icons/dr1.png", 150, 120));
        doctorLabel.setBounds(1400, 15, 150, 120);
        headerPanel.add(doctorLabel);

        // Dashboard panel for buttons
        JPanel dashboardPanel = new JPanel(null);
        dashboardPanel.setBounds(20, 190, 1560, 650);
        dashboardPanel.setBackground(new Color(0, 0, 0, 0)); // Transparent
        add(dashboardPanel);

        // Button size
        int buttonWidth = 250;
        int buttonHeight = 60;

        // Row 1: Add entities and search room
        JButton b1 = createStyledButton("Add New Patient");
        b1.setBounds(50, 50, buttonWidth, buttonHeight);
        dashboardPanel.add(b1);

        JButton b2 = createStyledButton("Room");
        b2.setBounds(350, 50, buttonWidth, buttonHeight);
        dashboardPanel.add(b2);

        JButton b3 = createStyledButton("Department");
        b3.setBounds(650, 50, buttonWidth, buttonHeight);
        dashboardPanel.add(b3);

        JButton b9 = createStyledButton("Search Room");
        b9.setBounds(950, 50, buttonWidth, buttonHeight);
        dashboardPanel.add(b9);

        JButton b10 = createStyledButton("Add Employee");
        b10.setBounds(1250, 50, buttonWidth, buttonHeight);
        dashboardPanel.add(b10);

        // Row 2: Update and delete actions
        JButton b12 = createStyledButton("Update Employee");
        b12.setBounds(50, 170, buttonWidth, buttonHeight);
        dashboardPanel.add(b12);

        JButton b4 = createStyledButton("All Employee Info");
        b4.setBounds(350, 170, buttonWidth, buttonHeight);
        dashboardPanel.add(b4);

        JButton b5 = createStyledButton("Patient Info");
        b5.setBounds(650, 170, buttonWidth, buttonHeight);
        dashboardPanel.add(b5);

        JButton b7 = createStyledButton("Update Patient");
        b7.setBounds(950, 170, buttonWidth, buttonHeight);
        dashboardPanel.add(b7);

        JButton b14 = createStyledButton("Patient Discharge");
        b14.setBounds(1250, 170, buttonWidth, buttonHeight);
        dashboardPanel.add(b14);

        // Row 3: Delete employee, ambulance, and logout
        JButton b13 = createStyledButton("Delete Employee");
        b13.setBounds(50, 290, buttonWidth, buttonHeight);
        dashboardPanel.add(b13);

        JButton b8 = createStyledButton("Hospital Ambulance");
        b8.setBounds(350, 290, buttonWidth, buttonHeight);
        dashboardPanel.add(b8);

        JButton b16 = createStyledButton("Medical Shop");
        b16.setBounds(650, 290, buttonWidth, buttonHeight);
        dashboardPanel.add(b16);

        JButton b11 = createStyledButton("Logout");
        b11.setBounds(1250, 290, buttonWidth, buttonHeight);
        dashboardPanel.add(b11);

        JButton b20 = createStyledButton("Insurance");
        b20.setBounds(950, 290, buttonWidth, buttonHeight);
        dashboardPanel.add(b20);


        // Button actions
        b20.addActionListener(e -> {
            try {
                new HospitalChatBot();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error in AI Chatbot");
            }
        });
        b16.addActionListener(e -> {
            try {
                new MedicalShop();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error opening Medical Shop", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        b1.addActionListener(e -> {
            try {
                new NEW_PATIENT();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error opening New Patient form!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        b2.addActionListener(e -> {
            try {
                new Room();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error opening Room form!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        b3.addActionListener(e -> {
            try {
                new Department();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error opening Department form!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        b4.addActionListener(e -> {
            try {
                new Employee_info();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error opening Employee Info!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        b5.addActionListener(e -> {
            try {
                new ALL_Patient_Info();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error opening Patient Info!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        b7.addActionListener(e -> {
            try {
                new update_patient_details();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error updating Patient details!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        b8.addActionListener(e -> {
            try {
                new Ambulance();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error opening Ambulance Info!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        b9.addActionListener(e -> {
            try {
                new SearchRoom();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error searching Room!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        b10.addActionListener(e -> {
            try {
                new ADD_EMPLOYEE();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error opening Add Employee form!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        b11.addActionListener(e -> {   //   Logout
            dispose();
            new Login();
        });

        b12.addActionListener(e -> {
            try {
                new UPDATE_EMPLOYEE();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error opening Update Employee form!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        b13.addActionListener(e -> {
            try {
                new DELETE_EMPLOYEE();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error opening Delete Employee form!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        b14.addActionListener(e -> {
            try {
                new patient_discharge();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error opening patient_discharge!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }

    // Create styled button with dynamic coloring
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gradient;
                if (getModel().isPressed()) {
                    gradient = new GradientPaint(0, 0, new Color(0, 91, 175), 0, getHeight(), new Color(0, 91, 175));
                } else if (getModel().isRollover()) {
                    gradient = new GradientPaint(0, 0, new Color(179, 229, 252), 0, getHeight(), new Color(163, 216, 244));
                } else {
                    gradient = new GradientPaint(0, 0, new Color(163, 216, 244), 0, getHeight(), new Color(0, 120, 215));
                }
                g2.setPaint(gradient);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.setColor(getForeground());
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        button.setFont(new Font("Tahoma", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);

        // Hover and press effects
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(new Color(240, 240, 240));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.WHITE);
            }
        });

        return button;
    }

    // Load image with transparency
    private Icon loadTransparentImage(String path, int w, int h, float alpha) {
        try {
            URL resourceUrl = ClassLoader.getSystemResource(path);
            if (resourceUrl == null) {
                return createPlaceholder(w, h, "Image Missing");
            }
            ImageIcon icon = new ImageIcon(resourceUrl);
            BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = img.createGraphics();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2d.drawImage(icon.getImage(), 0, 0, w, h, null);
            g2d.dispose();
            return new ImageIcon(img);
        } catch (Exception e) {
            return createPlaceholder(w, h, "Image Missing");
        }
    }

    // Load regular image
    private Icon loadImage(String path, int w, int h) {
        try {
            URL resourceUrl = ClassLoader.getSystemResource(path);
            if (resourceUrl == null) {
                return createPlaceholder(w, h, "Image Missing");
            }
            ImageIcon icon = new ImageIcon(resourceUrl);
            Image img = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception e) {
            return createPlaceholder(w, h, "Image Missing");
        }
    }

    // Create placeholder image if loading fails
    private Icon createPlaceholder(int w, int h, String text) {
        BufferedImage placeholder = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = placeholder.createGraphics();
        g.setColor(new Color(200, 200, 200));
        g.fillRect(0, 0, w, h);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Tahoma", Font.PLAIN, 14));
        g.drawString(text, 10, h / 2);
        g.dispose();
        return new ImageIcon(placeholder);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Reception());
    }
}