package tms.project.pkg1;

import javax.swing.*;
import java.awt.*;

public class Overview extends JFrame {

    public Overview() {
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0, 51, 102)); // Dark blue background

        JLabel title = new JLabel("Overview", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panel.add(title, BorderLayout.NORTH);

        JTextArea overviewArea = new JTextArea(20, 50);
        overviewArea.setEditable(false);
        overviewArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        overviewArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        overviewArea.setText("This is the overview page of the Tour Management System.\n\nHere, you can provide high-level information about the system, such as:\n- Total number of tours\n- Total number of users\n- Statistics on bookings\n- Recent activities\n- Upcoming tours\n\nYou can enhance this section with more detailed information as per your requirements.");

        panel.add(new JScrollPane(overviewArea), BorderLayout.CENTER);

        JButton backButton = new JButton("Back to Home");
        backButton.setBackground(new Color(255, 204, 102)); // Light orange background
        backButton.setForeground(new Color(0, 51, 102)); // Dark blue text color
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.addActionListener(e -> goBackToHome());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0, 51, 102)); // Dark blue background
        buttonPanel.add(backButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(panel);
        setTitle("Overview");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void goBackToHome() {
        new Homes().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Overview().setVisible(true);
        });
    }
}