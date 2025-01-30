package tms.project.pkg1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomePanel extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;

    // Constructor to initialize the components
    public HomePanel() {
        setTitle("Home Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);  // Set the size of the window
        setLocationRelativeTo(null);  // Center the window

        // Create a CardLayout to switch between panels
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add the panels to the mainPanel
        mainPanel.add(new ApplicationsJF(), "Applications");
        mainPanel.add(new ServicesJF(), "Services");
        mainPanel.add(new OverviewPanel(), "Overview");

        // Set the layout for the frame
        this.setLayout(new BorderLayout());

        // Create the sidebar and add it to the frame
        this.add(createSidebar(), BorderLayout.WEST);
        this.add(mainPanel, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }

    // Method to create the sidebar with buttons
    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(45, 45, 45));

        JButton applicationsButton = createSidebarButton("Applications");
        JButton servicesButton = createSidebarButton("Services");
        JButton overviewButton = createSidebarButton("Overview");

        // Add action listeners to buttons
        applicationsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Applications");
            }
        });
        servicesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Services");
            }
        });
        overviewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Overview");
            }
        });

        // Add buttons to the sidebar
        sidebar.add(applicationsButton);
        sidebar.add(servicesButton);
        sidebar.add(overviewButton);

        return sidebar;
    }

    // Method to create a styled sidebar button
    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(new Color(164, 0, 0));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(200, 50));
        button.setMaximumSize(new Dimension(200, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Login();  // Show the login window first
            }
        });
    }
}
