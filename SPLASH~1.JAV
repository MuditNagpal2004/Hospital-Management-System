package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.*;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends JFrame implements ActionListener {

    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton, cancelButton;
    JLabel animatedLabel;
    StringBuilder fullText = new StringBuilder();

    String[] introLines = {
            "Hospital Management System Project",
            "Made by",
            "1. Mudit Nagpal",
            "2. Navjot Singh Dhaliwal",
            "3. Inderpartap Singh",
            "4. Naman Thakur"
    };

    int lineIndex = 0;

    public SplashScreen() {
        setTitle("Lifeline Multispeciality Hospital");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);


        // ==== Background Image with Light Overlay ====
        URL bgURL = ClassLoader.getSystemResource("Icons/lifetime.png");
        if (bgURL == null) {
            JOptionPane.showMessageDialog(this, "Error: lifetime.png not found in /Icons/");
            System.exit(1);
        }
        ImageIcon bgIcon = new ImageIcon(bgURL);
        Image bgImg = bgIcon.getImage().getScaledInstance(1000, 700, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(bgImg)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Apply semi-transparent white overlay to lighten the background
                g.setColor(new Color(255, 255, 255, 180)); // 70% opacity
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        background.setBounds(0, 0, 1000, 700);
        add(background);

        // ==== Logo ====
        URL logoURL = ClassLoader.getSystemResource("Icons/Hospitals.png");
        if (logoURL == null) {
            JOptionPane.showMessageDialog(this, "Error: Hospitals.png not found in /Icons/");
            System.exit(1);
        }
        ImageIcon icon = new ImageIcon(logoURL);
        Image img = icon.getImage().getScaledInstance(350, 300, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(img));
        logoLabel.setBounds(320, 50, 350, 300);
        background.add(logoLabel);

        // ==== Animated Text ====
        animatedLabel = new JLabel("", JLabel.CENTER);
        animatedLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        animatedLabel.setForeground(Color.BLACK);

        animatedLabel.setBounds(200, 320, 600, 300);

        background.add(animatedLabel);

        setVisible(true);
        startTextAnimation();
    }

    private void startTextAnimation() {
        Timer timer = new Timer();
        fullText.setLength(0);

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (lineIndex < introLines.length) {
                    String line = introLines[lineIndex];

                    String styledLine = switch (lineIndex) {
                        case 0 -> "<span style='color:#000000; font-size:24px;'><b>" + line + "</b></span>";
                        case 1 -> "<span style='color:#000000; font-size:22px;'><b>" + line + "</b></span>";
                        case 2 -> "<span style='color:#000000; font-size:20px;'>" + line + "</span>";
                        case 3 -> "<span style='color:#000000; font-size:20px;'>" + line + "</span>";
                        case 4 -> "<span style='color:#000000; font-size:20px;'>" + line + "</span>";
                        case 5 -> "<span style='color:#000000; font-size:20px;'>" + line + "</span>";
                        default -> line;
                    };

                    fullText.append(styledLine).append("<br>");
                    animatedLabel.setText("<html><center>" + fullText + "</center></html>");
                    lineIndex++;
                } else {
                    timer.cancel();
                    showGifSplash();
                }
            }
        }, 0, 800); // one line every 800ms
    }

    private void showGifSplash() {
        getContentPane().removeAll();
        repaint();

        JPanel gifPanel = new JPanel(new BorderLayout());
        gifPanel.setBackground(Color.WHITE);

        URL gifURL = ClassLoader.getSystemResource("Icons/PreventiveHealthCare.gif");
        if (gifURL == null) {
            JOptionPane.showMessageDialog(this, "Error: PreventiveHealthCare.gif not found in /Icons/");
            System.exit(1);
        }
        ImageIcon gifIcon = new ImageIcon(gifURL);
        JLabel gifLabel = new JLabel(gifIcon, JLabel.CENTER);
        gifPanel.add(gifLabel, BorderLayout.CENTER);

        setContentPane(gifPanel);
        revalidate();
        repaint();

        new Timer().schedule(new TimerTask() {
            public void run() {
                SwingUtilities.invokeLater(() -> showLoginForm());
            }
        }, 3000);
    }

    private void showLoginForm() {
        setSize(900, 500);
        setLocationRelativeTo(null);
        getContentPane().removeAll();
        repaint();

        // ==== Background Image (Unmodified) ====
        URL bgURL = ClassLoader.getSystemResource("Icons/lifetime.png");
        ImageIcon bgIcon = new ImageIcon(bgURL);
        Image bgImage = bgIcon.getImage().getScaledInstance(900, 500, Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(bgImage));
        backgroundLabel.setBounds(0, 0, 900, 500);
        add(backgroundLabel);

        // ==== Login Panel ====
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBounds(480, 60, 360, 380);
        loginPanel.setBackground(new Color(255, 255, 255, 230));
        loginPanel.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1, true));
        backgroundLabel.add(loginPanel);

        JLabel title = new JLabel("Admin Login");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(0, 102, 102));
        title.setBounds(110, 30, 200, 30);
        loginPanel.add(title);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(40, 90, 100, 20);
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        loginPanel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(40, 115, 280, 30);
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        loginPanel.add(usernameField);

        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(40, 160, 100, 20);
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        loginPanel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(40, 185, 280, 30);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        loginPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(40, 240, 130, 40);
        loginButton.setBackground(new Color(0, 123, 138));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(this);
        loginPanel.add(loginButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(190, 240, 130, 40);
        cancelButton.setBackground(new Color(220, 53, 69));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cancelButton.setFocusPainted(false);
        cancelButton.addActionListener(this);
        loginPanel.add(cancelButton);

        // ==== Side Logo ====
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("Icons/Hospitals.png"));
        Image img = icon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(img));
        logo.setBounds(20, 50, 400, 400);
        backgroundLabel.add(logo);

        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String user = usernameField.getText().trim();
            String pass = new String(passwordField.getPassword()).trim();
            try {
                Conn c = new Conn();
                PreparedStatement pst = c.connection.prepareStatement("SELECT * FROM Login WHERE ID = ? AND PW = ?");
                pst.setString(1, user);
                pst.setString(2, pass);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login Successful!");
                    new Reception();
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Username or Password");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database error!");
            }
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new SplashScreen();
    }
}