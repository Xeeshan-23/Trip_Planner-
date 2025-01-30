package tms.project.pkg1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminPanel extends JFrame {

    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JTable applicationTable;
    private DefaultTableModel applicationTableModel;
    private JTable serviceTable;
    private DefaultTableModel serviceTableModel;

    public AdminPanel() {
        initComponents();
        loadApplications();
    }

    private void initComponents() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel applicationPanel = createApplicationPanel();
        JPanel servicePanel = createServicePanel();

        mainPanel.add(applicationPanel, "Applications");
        mainPanel.add(servicePanel, "Services");

        this.add(mainPanel);
        setTitle("Admin Panel");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel createApplicationPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0, 51, 102)); // Dark blue background

        JLabel title = new JLabel("Tour Applications", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panel.add(title, BorderLayout.NORTH);

        applicationTableModel = new DefaultTableModel(new Object[]{"Select", "ID", "Location", "Start Date", "End Date"}, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Boolean.class;
                    case 1:
                        return Integer.class;
                    case 2:
                    case 3:
                    case 4:
                        return String.class;
                    default:
                        return String.class;
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }
        };

        applicationTable = new JTable(applicationTableModel);
        JScrollPane scrollPane = new JScrollPane(applicationTable);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0, 51, 102)); // Dark blue background

        JButton acceptBtn = createStyledButton("Accept");
        JButton rejectBtn = createStyledButton("Reject");
        JButton nextBtn = createStyledButton("Proceed to Services");

        acceptBtn.addActionListener(e -> acceptApplications());
        rejectBtn.addActionListener(e -> rejectApplications());
        nextBtn.addActionListener(e -> showServicesPanel());

        buttonPanel.add(acceptBtn);
        buttonPanel.add(rejectBtn);
        buttonPanel.add(nextBtn);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createServicePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0, 51, 102)); // Dark blue background

        JLabel title = new JLabel("Service Requests", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panel.add(title, BorderLayout.NORTH);

        serviceTableModel = new DefaultTableModel(new Object[]{"Select", "ID", "Service", "Details"}, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Boolean.class;
                    case 1:
                        return Integer.class;
                    case 2:
                    case 3:
                        return String.class;
                    default:
                        return String.class;
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }
        };

        serviceTable = new JTable(serviceTableModel);
        JScrollPane scrollPane = new JScrollPane(serviceTable);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0, 51, 102)); // Dark blue background

        JButton acceptBtn = createStyledButton("Accept");
        JButton rejectBtn = createStyledButton("Reject");
        JButton backBtn = createStyledButton("Back to Applications");
        JButton homeBtn = createStyledButton("Back to Home");

        acceptBtn.addActionListener(e -> acceptServiceRequests());
        rejectBtn.addActionListener(e -> rejectServiceRequests());
        backBtn.addActionListener(e -> showApplicationsPanel());
        homeBtn.addActionListener(e -> goBackToHome());

        buttonPanel.add(acceptBtn);
        buttonPanel.add(rejectBtn);
        buttonPanel.add(backBtn);
        buttonPanel.add(homeBtn);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(255, 204, 102)); // Light orange background
        button.setForeground(new Color(0, 51, 102)); // Dark blue text color
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    private void loadApplications() {
        Connection conn = DatabaseConnection.connect();
        if (conn != null) {
            String sql = "SELECT id, location, start_date, end_date FROM pending_applications";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                applicationTableModel.setRowCount(0); // Clear the table

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String location = rs.getString("location");
                    String startDate = rs.getString("start_date");
                    String endDate = rs.getString("end_date");

                    applicationTableModel.addRow(new Object[]{false, id, location, startDate, endDate});
                }

                if (applicationTableModel.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "No tour applications yet.", "Information", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Failed to load applications: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void acceptApplications() {
        ArrayList<Integer> selectedIds = getSelectedApplicationIds();
        if (!selectedIds.isEmpty()) {
            moveApplicationsToAccepted(selectedIds);
            JOptionPane.showMessageDialog(this, "Selected applications accepted!");
            loadApplications();
        } else {
            JOptionPane.showMessageDialog(this, "Please select applications to accept.");
        }
    }

    private void rejectApplications() {
        ArrayList<Integer> selectedIds = getSelectedApplicationIds();
        if (!selectedIds.isEmpty()) {
            deleteApplications(selectedIds);
            JOptionPane.showMessageDialog(this, "Selected applications rejected!");
            loadApplications();
        } else {
            JOptionPane.showMessageDialog(this, "Please select applications to reject.");
        }
    }

    private ArrayList<Integer> getSelectedApplicationIds() {
        ArrayList<Integer> selectedIds = new ArrayList<>();
        for (int i = 0; i < applicationTableModel.getRowCount(); i++) {
            if ((Boolean) applicationTableModel.getValueAt(i, 0)) {
                selectedIds.add((Integer) applicationTableModel.getValueAt(i, 1));
            }
        }
        return selectedIds;
    }

    private void moveApplicationsToAccepted(ArrayList<Integer> ids) {
        Connection conn = DatabaseConnection.connect();
        if (conn != null) {
            String sqlInsert = "INSERT INTO accepted_applications (location, start_date, end_date) SELECT location, start_date, end_date FROM pending_applications WHERE id = ?";
            String sqlDelete = "DELETE FROM pending_applications WHERE id = ?";
            try (PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert);
                 PreparedStatement pstmtDelete = conn.prepareStatement(sqlDelete)) {
                for (int id : ids) {
                    pstmtInsert.setInt(1, id);
                    pstmtInsert.executeUpdate();
                    pstmtDelete.setInt(1, id);
                    pstmtDelete.executeUpdate();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Failed to accept applications: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteApplications(ArrayList<Integer> ids) {
        Connection conn = DatabaseConnection.connect();
        if (conn != null) {
            String sql = "DELETE FROM pending_applications WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                for (int id : ids) {
                    pstmt.setInt(1, id);
                    pstmt.executeUpdate();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Failed to reject applications: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showServicesPanel() {
        loadServiceRequests();
        cardLayout.show(mainPanel, "Services");
    }

    private void showApplicationsPanel() {
        cardLayout.show(mainPanel, "Applications");
    }

    private void loadServiceRequests() {
        Connection conn = DatabaseConnection.connect();
        if (conn != null) {
            String sql = "SELECT id, service_type, details FROM service_requests";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                serviceTableModel.setRowCount(0); // Clear the table

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String serviceType = rs.getString("service_type");
                    String details = rs.getString("details");

                    serviceTableModel.addRow(new Object[]{false, id, serviceType, details});
                }

                if (serviceTableModel.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "No service requests yet.", "Information", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Failed to load service requests: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void acceptServiceRequests() {
        ArrayList<Integer> selectedIds = getSelectedServiceRequestIds();
        if (!selectedIds.isEmpty()) {
            processServiceRequests(selectedIds, true);
            JOptionPane.showMessageDialog(this, "Selected service requests accepted!");
            loadServiceRequests();
        } else {
            JOptionPane.showMessageDialog(this, "Please select service requests to accept.");
        }
    }

    private void rejectServiceRequests() {
        ArrayList<Integer> selectedIds = getSelectedServiceRequestIds();
        if (!selectedIds.isEmpty()) {
            processServiceRequests(selectedIds, false);
            JOptionPane.showMessageDialog(this, "Selected service requests rejected!");
            loadServiceRequests();
        } else {
            JOptionPane.showMessageDialog(this, "Please select service requests to reject.");
        }
    }

    private ArrayList<Integer> getSelectedServiceRequestIds() {
        ArrayList<Integer> selectedIds = new ArrayList<>();
        for (int i = 0; i < serviceTableModel.getRowCount(); i++) {
            if ((Boolean) serviceTableModel.getValueAt(i, 0)) {
                selectedIds.add((Integer) serviceTableModel.getValueAt(i, 1));
            }
        }
        return selectedIds;
    }

    private void processServiceRequests(ArrayList<Integer> ids, boolean accept) {
        Connection conn = DatabaseConnection.connect();
        if (conn != null) {
            String sqlDelete = "DELETE FROM service_requests WHERE id = ?";
            try (PreparedStatement pstmtDelete = conn.prepareStatement(sqlDelete)) {
                for (int id : ids) {
                    pstmtDelete.setInt(1, id);
                    pstmtDelete.executeUpdate();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Failed to process service requests: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void goBackToHome() {
        new Homes().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdminPanel().setVisible(true);
        });
    }
}