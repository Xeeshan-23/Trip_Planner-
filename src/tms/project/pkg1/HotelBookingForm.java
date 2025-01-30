package tms.project.pkg1;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HotelBookingForm extends JFrame {
    public HotelBookingForm() {
        initComponents();
    }

    private void initComponents() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel nameLabel = new JLabel("Hotel Name:");
        JTextField nameField = new JTextField(15);

        JLabel roomLabel = new JLabel("Room Type:");
        JTextField roomField = new JTextField(15);

        JButton submitBtn = createSubmitButton(nameField, roomField);

        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(nameLabel, gbc);
        gbc.gridx = 1; formPanel.add(nameField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(roomLabel, gbc);
        gbc.gridx = 1; formPanel.add(roomField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; formPanel.add(submitBtn, gbc);

        this.add(formPanel);
        setTitle("Hotel Booking");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JButton createSubmitButton(JTextField nameField, JTextField roomField) {
        JButton button = new JButton("Submit");
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(new Color(0, 0, 139));
        button.setForeground(Color.WHITE);

        button.addActionListener(e -> {
            String hotelName = nameField.getText().trim();
            String roomType = roomField.getText().trim();

            if (hotelName.isEmpty() || roomType.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                saveServiceRequest("Hotel Booking", "Hotel: " + hotelName + ", Room Type: " + roomType);
                JOptionPane.showMessageDialog(this, "Hotel Booking Submitted!");
                new ServicesJF().setVisible(true);
                this.dispose();
            }
        });
        return button;
    }

    private void saveServiceRequest(String serviceType, String details) {
        Connection conn = DatabaseConnection.connect();
        if (conn != null) {
            String sql = "INSERT INTO service_requests (service_type, details) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, serviceType);
                pstmt.setString(2, details);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Failed to save service request: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HotelBookingForm().setVisible(true));
    }
}