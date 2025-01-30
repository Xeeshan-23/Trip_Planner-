package tms.project.pkg1;

import javax.swing.*;
import java.awt.*;

public class CabBookingForm extends JFrame {
    public CabBookingForm() {
        initComponents();
    }

    private void initComponents() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel pickupLabel = new JLabel("Pickup Location:");
        JTextField pickupField = new JTextField(15);

        JLabel dropoffLabel = new JLabel("Drop Off Location:");
        JTextField dropoffField = new JTextField(15);

        JButton submitBtn = createSubmitButton(pickupField, dropoffField);

        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(pickupLabel, gbc);
        gbc.gridx = 1; formPanel.add(pickupField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(dropoffLabel, gbc);
        gbc.gridx = 1; formPanel.add(dropoffField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; formPanel.add(submitBtn, gbc);

        this.add(formPanel);
        setTitle("Cab Booking");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JButton createSubmitButton(JTextField pickupField, JTextField dropoffField) {
        JButton button = new JButton("Submit");
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(new Color(0, 0, 139));
        button.setForeground(Color.WHITE);

        button.addActionListener(e -> {
            String details = "Cab Booking: Pickup - " + pickupField.getText() + ", Dropoff - " + dropoffField.getText();
            ServicesJF.addBooking(details);
            JOptionPane.showMessageDialog(null, "Cab Booking Submitted!");
            new ServicesJF().setVisible(true);
            this.dispose();
        });
        return button;
    }
}
