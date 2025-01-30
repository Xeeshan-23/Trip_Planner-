package tms.project.pkg1;

import javax.swing.*;
import java.awt.*;

public class GuideHiringForm extends JFrame {
    public GuideHiringForm() {
        initComponents();
    }

    private void initComponents() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel locationLabel = new JLabel("Location:");
        JTextField locationField = new JTextField(15);

        JLabel durationLabel = new JLabel("Duration (days):");
        JTextField durationField = new JTextField(15);

        JButton submitBtn = createSubmitButton(locationField, durationField);

        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(locationLabel, gbc);
        gbc.gridx = 1; formPanel.add(locationField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(durationLabel, gbc);
        gbc.gridx = 1; formPanel.add(durationField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; formPanel.add(submitBtn, gbc);

        this.add(formPanel);
        setTitle("Guide Hiring");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JButton createSubmitButton(JTextField locationField, JTextField durationField) {
        JButton button = new JButton("Submit");
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(new Color(0, 0, 139));
        button.setForeground(Color.WHITE);

        button.addActionListener(e -> {
            String details = "Guide Hiring: Location - " + locationField.getText() + ", Duration - " + durationField.getText() + " days";
            ServicesJF.addBooking(details);
            JOptionPane.showMessageDialog(null, "Guide Hiring Request Submitted!");
            new ServicesJF().setVisible(true);
            this.dispose();
        });
        return button;
    }
}
