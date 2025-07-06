package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HospitalChatBot extends JFrame {

    private JTextArea output;
    private JTextField input;
    private JLabel question;

    private String[] details = new String[4]; // name, age, address, phone
    private boolean insured, collecting;
    private int step;
    private String name;

    // Predefined insured patients
    private final String[][] patients = {
            {"Amit Sharma", "123456789012"},
            {"Priya Verma", "234567890123"},
            {"Rajesh Kumar", "345678901234"},
            {"Meena Joshi", "456789012345"},
            {"Vikas Singh", "567890123456"}
    };

    public HospitalChatBot() {
        setTitle("Hospital Insurance ChatBot");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Heading
        JLabel heading = new JLabel("Hospital Insurance Assistant", SwingConstants.CENTER);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 24));
        heading.setForeground(Color.WHITE);
        heading.setOpaque(true);
        heading.setBackground(new Color(0, 102, 204));
        add(heading, BorderLayout.NORTH);

        // Output text area
        output = new JTextArea();
        output.setFont(new Font("Arial", Font.PLAIN, 16));
        output.setEditable(false);
        output.setLineWrap(true);
        output.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(output);
        add(scrollPane, BorderLayout.CENTER);

        // Input Panel
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        question = new JLabel("Do you have insurance? (yes/no)");
        question.setFont(new Font("Arial", Font.BOLD, 16));
        bottomPanel.add(question, BorderLayout.NORTH);

        input = new JTextField();
        input.setFont(new Font("Arial", Font.PLAIN, 16));
        bottomPanel.add(input, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton submit = new JButton("Submit");
        JButton view = new JButton("View Patients");
        buttonPanel.add(submit);
        buttonPanel.add(view);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        // Event Listeners
        submit.addActionListener(e -> process());
        input.addActionListener(e -> process());
        view.addActionListener(e -> showAllPatients());

        // Welcome Text
        output.append("Welcome to the Hospital Insurance ChatBot\n");
        output.append("Do you have insurance? (yes/no)\n");

        setVisible(true);
    }

    // Chatbot processing logic
    private void process() {
        String text = input.getText().trim();
        if (text.isEmpty()) return;

        output.append("You: " + text + "\n");
        String reply;

        if (!collecting) {
            if (text.equalsIgnoreCase("yes")) {
                insured = true;
                collecting = true;
                step = 0;
                question.setText("Enter your Aadhar Number:");
                reply = "Please enter your 12-digit Aadhar number:";
            } else if (text.equalsIgnoreCase("no")) {
                collecting = true;
                step = 1;
                question.setText("Enter your Name:");
                reply = "Please enter your full name to register:";
            } else {
                reply = "Please reply with 'yes' or 'no'.";
            }
        } else if (insured && step == 0) {
            if (!text.matches("\\d{12}")) {
                reply = "Invalid Aadhar! Must be 12 digits.";
            } else {
                boolean found = false;
                for (String[] p : patients) {
                    if (p[1].equals(text)) {
                        name = p[0];
                        found = true;
                        break;
                    }
                }
                if (found) {
                    collecting = false;
                    question.setText("Verified");
                    showCertificate(true);
                    reply = "Verification successful. Insurance coverage granted.";
                } else {
                    reply = "Aadhar not found. You may register instead.";
                }
            }
        } else {
            switch (step) {
                case 1 -> {
                    details[0] = text;
                    name = text;
                    step++;
                    question.setText("Enter Age:");
                    reply = "Please enter your age:";
                }
                case 2 -> {
                    if (!text.matches("\\d+")) {
                        reply = "Age must be numeric.";
                    } else {
                        details[1] = text;
                        step++;
                        question.setText("Enter Address:");
                        reply = "Enter your address:";
                    }
                }
                case 3 -> {
                    details[2] = text;
                    step++;
                    question.setText("Enter Phone Number:");
                    reply = "Enter your phone number:";
                }
                case 4 -> {
                    details[3] = text;
                    collecting = false;
                    step = 0;
                    question.setText("Registration Completed");
                    showCertificate(false);
                    reply = "Registration complete. Insurance coverage granted.";
                }
                default -> reply = "Unexpected input.";
            }
        }

        output.append("System: " + reply + "\n\n");
        input.setText("");
    }

    // Certificate pop-up
    private void showCertificate(boolean isInsured) {
        JTextArea cert = new JTextArea();
        cert.setFont(new Font("Courier New", Font.BOLD, 14));
        cert.setEditable(false);
        cert.setBackground(Color.WHITE);
        cert.setForeground(Color.DARK_GRAY);
        cert.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));

        StringBuilder sb = new StringBuilder();
        sb.append("********************************************\n");
        sb.append("     HOSPITAL INSURANCE CERTIFICATE\n");
        sb.append("********************************************\n\n");
        sb.append("Name      : ").append(name).append("\n");
        if (isInsured) {
            sb.append("Status    : Existing Insured\n");
            sb.append("Coverage  : 75%\n");
        } else {
            sb.append("Age       : ").append(details[1]).append("\n");
            sb.append("Address   : ").append(details[2]).append("\n");
            sb.append("Phone     : ").append(details[3]).append("\n");
            sb.append("Status    : Newly Registered\n");
            sb.append("Coverage  : 10%\n");
        }
        sb.append("Issued On : ").append(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a"))).append("\n");

        cert.setText(sb.toString());

        // Show in scroll pane popup
        JScrollPane scroll = new JScrollPane(cert);
        scroll.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(this, scroll, "Insurance Certificate", JOptionPane.INFORMATION_MESSAGE);
    }

    // View all predefined insured patients
    private void showAllPatients() {
        StringBuilder sb = new StringBuilder("Hospital Insured Patients:\n\n");
        for (String[] p : patients) {
            sb.append("Name: ").append(p[0]).append("\nAadhar: ").append(p[1]).append("\n\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Patient List", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HospitalChatBot::new);
    }
}
