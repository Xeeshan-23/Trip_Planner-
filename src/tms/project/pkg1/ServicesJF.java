package tms.project.pkg1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ServicesJF extends JFrame {
    private static ArrayList<String> bookingDetails = new ArrayList<>();

    public static void addBooking(String detail) {
        bookingDetails.add(detail);
    }

    public static ArrayList<String> getBookings() {
        return bookingDetails;
    }

    static ArrayList<String> getApplications() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ServicesJF() {
        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton cabBookingBtn = createServiceButton("Cab Booking");
        JButton hotelBookingBtn = createServiceButton("Hotel Booking");
        JButton guideHiringBtn = createServiceButton("Guide Hiring");
        JButton adminPanelBtn = createServiceButton("Admin Panel");

        adminPanelBtn.addActionListener(e -> {
            new AdminLoginForm().setVisible(true);
            this.dispose();
        });

        gbc.gridx = 0; gbc.gridy = 0; mainPanel.add(cabBookingBtn, gbc);
        gbc.gridy = 1; mainPanel.add(hotelBookingBtn, gbc);
        gbc.gridy = 2; mainPanel.add(guideHiringBtn, gbc);
        gbc.gridy = 3; mainPanel.add(adminPanelBtn, gbc);

        this.add(mainPanel);
        setTitle("Services");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JButton createServiceButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(new Color(0, 0, 139));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(200, 50));
        button.addActionListener(e -> handleServiceClick(text));
        return button;
    }

    private void handleServiceClick(String serviceType) {
        switch (serviceType) {
            case "Cab Booking" -> new CabBookingForm().setVisible(true);
            case "Hotel Booking" -> new HotelBookingForm().setVisible(true);
            case "Guide Hiring" -> new GuideHiringForm().setVisible(true);
        }
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ServicesJF::new);
    }
}
