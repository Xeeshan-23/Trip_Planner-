package tms.project.pkg1; // Ensure this is correct

import javax.swing.*;
import java.awt.*;

public class OverviewPanel extends JPanel {
    public OverviewPanel() {
        setLayout(new BorderLayout());
        JLabel overviewLabel = new JLabel("Welcome to the Tour Management System", JLabel.CENTER);
        overviewLabel.setFont(new Font("Arial", Font.BOLD, 24));
        overviewLabel.setForeground(Color.WHITE);
        add(overviewLabel, BorderLayout.CENTER);
        setBackground(Color.BLACK); // Example styling, adjust as needed
    }
}
