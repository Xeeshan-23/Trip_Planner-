package tms.project.pkg1;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame {

    // Components
    private JPanel mainPanel;
    private JLabel titleLabel, emailLabel, passwordLabel, messageLabel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    public Login() {
        // Initialize components and layout
        initComponents();
        setTitle("Login - TMS");
        setSize(800, 600); // Adjusted size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setResizable(true); // Allow resizing
    }

    private void initComponents() {
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(255, 255, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        titleLabel = new JLabel("Welcome to TMS");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(new Color(0, 0, 139));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        // Email Label and Field
        emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        emailLabel.setForeground(new Color(0, 0, 139));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(emailLabel, gbc);

        emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(emailField, gbc);

        // Password Label and Field
        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        passwordLabel.setForeground(new Color(0, 0, 139));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(passwordField, gbc);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 0, 139));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.addActionListener(evt -> handleLogin());
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(loginButton, gbc);

        // Register Button
        registerButton = new JButton("Register");
        registerButton.setBackground(Color.WHITE);
        registerButton.setForeground(new Color(0, 0, 139));
        registerButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        registerButton.addActionListener(evt -> handleRegister());
        gbc.gridx=0;
        gbc.gridy = 4;
        mainPanel.add(registerButton, gbc);

        // Message Label
        messageLabel = new JLabel();
        messageLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        messageLabel.setForeground(Color.RED);
        gbc.gridy = 5;
        mainPanel.add(messageLabel, gbc);

        add(mainPanel);
    }

    private void handleLogin() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Please fill in both fields.");
            return;
        }

        if (validateLogin(email, password)) {
            JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            new Homes().setVisible(true); // Replace with your Home JFrame
        } else {
            messageLabel.setText("Invalid email or password.");
        }
    }

    private boolean validateLogin(String email, String password) {
        String dbUrl = "jdbc:mysql://localhost:3306/user"; // Replace 'user' with your database name
        String dbUser = "root"; // Replace with your MySQL username
        String dbPassword = "1221"; // Replace with your MySQL password

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, email);
                stmt.setString(2, password);

                try (ResultSet rs = stmt.executeQuery()) {
                    return rs.next(); // Returns true if a matching user is found
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    private void handleRegister() {
        this.dispose();
        new RegisterJF().setVisible(true); // Replace with your Register JFrame
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}
