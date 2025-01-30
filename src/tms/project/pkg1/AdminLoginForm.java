package tms.project.pkg1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AdminLoginForm extends JFrame {
    // Hardcoded admin credentials
    private final String adminEmail = "admin@example.com";
    private final String adminPassword = "admin123";

    public AdminLoginForm() {
        initComponents();
    }

    private void initComponents() {
        // Create the main login panel
        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Email label and field
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(15);

        // Password label and field
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);

        // Login button
        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(0, 0, 139));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));

        // ActionListener for the login button
        loginBtn.addActionListener((ActionEvent e) -> {
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            // Check credentials
            if (email.equals(adminEmail) && password.equals(adminPassword)) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                SwingUtilities.invokeLater(() -> {
                    new AdminPanel().setVisible(true); // Open AdminPanel
                    this.dispose(); // Close AdminLoginForm
                });
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add components to the login panel
        gbc.gridx = 0; gbc.gridy = 0; loginPanel.add(emailLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0; loginPanel.add(emailField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; loginPanel.add(passwordLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1; loginPanel.add(passwordField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        loginPanel.add(loginBtn, gbc);

        // Set up the frame
        this.add(loginPanel);
        setTitle("Admin Login");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Main method to launch the AdminLoginForm
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdminLoginForm().setVisible(true);
        });
    }
}
