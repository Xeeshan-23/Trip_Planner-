package tms.project.pkg1;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Homes extends JFrame {

    private JPanel sidebarPanel;
    private JPanel mainPanel;
    private JButton appButton, serviceButton, overviewButton, logoutButton, userButton;
    private JLabel titleLabel;
    private JPanel statsPanel;
    private JLabel availableLabel, bookedLabel, totalToursLabel;
    private JLabel welcomeMessage;
    private JPanel footerPanel;

    // Constructor with application details as parameters
    public Homes(String location, String startDate, String endDate) {
        initializeComponents();
        setupLayout();
        configureFrame();
        addEventListeners();
        updateWelcomeMessage(location, startDate, endDate); // Update the welcome message with application details
        loadApplications(); // Load applications from the database
    }

    // Default constructor for when no application is submitted
    public Homes() {
        initializeComponents();
        setupLayout();
        configureFrame();
        addEventListeners();
        loadApplications(); // Load applications from the database
    }

    private void initializeComponents() {
        sidebarPanel = new JPanel();
        sidebarPanel.setBackground(new Color(0, 51, 102)); // Dark blue background
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BorderLayout());

        titleLabel = new JLabel("Tour Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        appButton = createStyledButton("Tour Form");
        serviceButton = createStyledButton("Services");
        overviewButton = createStyledButton("Overview");
        logoutButton = createStyledButton("Logout");
        userButton = createStyledButton("Users");

        statsPanel = new JPanel();
        statsPanel.setBackground(new Color(0, 51, 102));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Tour Statistics"));

        availableLabel = new JLabel("Available Tours: 14");
        bookedLabel = new JLabel("Booked Tours: 5");
        totalToursLabel = new JLabel("Total Tours: 19");

        availableLabel.setForeground(Color.WHITE);
        bookedLabel.setForeground(Color.WHITE);
        totalToursLabel.setForeground(Color.WHITE);

        statsPanel.add(availableLabel);
        statsPanel.add(bookedLabel);
        statsPanel.add(totalToursLabel);

        welcomeMessage = new JLabel("Welcome to the Tour Management System!", JLabel.CENTER);
        welcomeMessage.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeMessage.setForeground(new Color(0, 51, 102)); // Dark blue for a professional look
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(0, 51, 102)); // Dark blue background
        button.setForeground(Color.WHITE); // White text color
        button.setFont(new Font("Segoe UI", Font.BOLD, 22));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        return button;
    }

    private void setupLayout() {
        sidebarPanel.add(Box.createVerticalStrut(50));
        sidebarPanel.add(titleLabel);
        sidebarPanel.add(Box.createVerticalStrut(30));
        sidebarPanel.add(userButton);
        sidebarPanel.add(Box.createVerticalStrut(10));
        sidebarPanel.add(serviceButton);
        sidebarPanel.add(Box.createVerticalStrut(10));
        sidebarPanel.add(appButton);
        sidebarPanel.add(Box.createVerticalStrut(10));
        sidebarPanel.add(overviewButton);
        sidebarPanel.add(Box.createVerticalStrut(10));
        sidebarPanel.add(logoutButton);

        add(sidebarPanel, BorderLayout.WEST);

        mainPanel.add(statsPanel, BorderLayout.NORTH);
        mainPanel.add(welcomeMessage, BorderLayout.CENTER);

        footerPanel = new JPanel();
        footerPanel.setBackground(new Color(0, 51, 102)); // Dark blue footer background
        JLabel footerLabel = new JLabel("© 2025 Tour Management System. All rights reserved.");
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        footerLabel.setForeground(Color.WHITE); // White color for footer text
        footerPanel.add(footerLabel);

        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
    }

    private void configureFrame() {
        setTitle("Tour Management System - Home");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void addEventListeners() {
        appButton.addActionListener(e -> openApplicationPage());
        serviceButton.addActionListener(e -> openServicePage());
        overviewButton.addActionListener(e -> openOverviewPage());
        logoutButton.addActionListener(e -> logout());
    }

    private void openApplicationPage() {
        this.setVisible(false);
        ApplicationsJF applicationForm = new ApplicationsJF();
        applicationForm.setVisible(true);
    }

    private void openServicePage() {
        this.setVisible(false);
        ServicesJF servicesPage = new ServicesJF();
        servicesPage.setVisible(true);
    }

    private void openOverviewPage() {
        this.setVisible(false);
        Overview overviewPage = new Overview();
        overviewPage.setVisible(true);
    }

    private void logout() {
        this.setVisible(false);
        Login loginPage = new Login();
        loginPage.setVisible(true);
    }

    private void updateWelcomeMessage(String location, String startDate, String endDate) {
        if (location != null && startDate != null && endDate != null) {
            welcomeMessage.setText(
                "<html>Welcome to the Tour Management System!<br>" +
                "Your recent application has been accepted:<br>" +
                "Location: " + location + "<br>" +
                "Start Date: " + startDate + "<br>" +
                "End Date: " + endDate + "</html>"
            );
        }
    }

    private void loadApplications() {
        Connection conn = DatabaseConnection.connect();
        if (conn != null) {
            String sql = "SELECT location, start_date, end_date FROM accepted_applications";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                JPanel applicationsPanel = new JPanel();
                applicationsPanel.setLayout(new BoxLayout(applicationsPanel, BoxLayout.Y_AXIS));
                applicationsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

                while (rs.next()) {
                    String location = rs.getString("location");
                    String startDate = rs.getString("start_date");
                    String endDate = rs.getString("end_date");

                    JPanel applicationBox = new JPanel();
                    applicationBox.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
                    applicationBox.setBackground(new Color(230, 230, 250)); // Light blue background
                    applicationBox.setLayout(new BoxLayout(applicationBox, BoxLayout.Y_AXIS));
                    applicationBox.setPreferredSize(new Dimension(500, 100));
                    applicationBox.setMaximumSize(new Dimension(500, 100));
                    applicationBox.setAlignmentX(Component.CENTER_ALIGNMENT);
                    applicationBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                    JLabel locationLabel = new JLabel("Location: " + location);
                    JLabel startDateLabel = new JLabel("Start Date: " + startDate);
                    JLabel endDateLabel = new JLabel("End Date: " + endDate);

                    locationLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
                    startDateLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
                    endDateLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

                    applicationBox.add(locationLabel);
                    applicationBox.add(startDateLabel);
                    applicationBox.add(endDateLabel);

                    applicationsPanel.add(Box.createVerticalStrut(10)); // Add space between application boxes
                    applicationsPanel.add(applicationBox);
                }

                JScrollPane scrollPane = new JScrollPane(applicationsPanel);
                scrollPane.setBorder(BorderFactory.createEmptyBorder());
                mainPanel.add(scrollPane, BorderLayout.CENTER);
                revalidate();
                repaint();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Failed to load applications: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Homes().setVisible(true);
        });
    }
}