package tms.project.pkg1;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;

public class ApplicationsJF extends javax.swing.JFrame {

    private static String submittedLocation = "";
    private static String submittedStartDate = "";
    private static String submittedEndDate = "";

    public ApplicationsJF() {
        initComponents();
    }

    public static String[] getSubmittedApplicationDetails() {
        return new String[]{submittedLocation, submittedStartDate, submittedEndDate};
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 139));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jLabel1.setForeground(java.awt.Color.WHITE);
        jLabel1.setText("Tour Details");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jLabel2.setForeground(java.awt.Color.WHITE);
        jLabel2.setText("Location");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jLabel3.setForeground(java.awt.Color.WHITE);
        jLabel3.setText("Start Date");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jLabel4.setForeground(java.awt.Color.WHITE);
        jLabel4.setText("End Date");

        jButton1.setBackground(new java.awt.Color(255, 204, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jButton1.setForeground(new java.awt.Color(0, 0, 139));
        jButton1.setText("Submit");

        jButton1.addActionListener(evt -> {
            submittedLocation = jTextField1.getText().trim();
            submittedStartDate = jTextField2.getText().trim();
            submittedEndDate = jTextField3.getText().trim();

            if (submittedLocation.isEmpty() || submittedStartDate.isEmpty() || submittedEndDate.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    submittedStartDate = parseDate(submittedStartDate);
                    submittedEndDate = parseDate(submittedEndDate);
                    saveApplicationToDatabase(submittedLocation, submittedStartDate, submittedEndDate);
                    JOptionPane.showMessageDialog(this, "Application submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    Homes hme = new Homes();
                    hme.setVisible(true);
                    dispose();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Invalid date format! Please use YYYY-MM-DD or DD.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jButton1)
                    .addGap(326, 326, 326))
                .addGroup(layout.createSequentialGroup()
                    .addGap(198, 198, 198)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2)
                                .addComponent(jLabel4))
                            .addGap(36, 36, 36)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(256, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(36)
                    .addComponent(jLabel1)
                    .addGap(63)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addGap(32)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGap(40)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                    .addComponent(jButton1)
                    .addGap(50))
        );

        getContentPane().setBackground(Color.WHITE);

        add(jPanel1);

        pack();
    }

    private String parseDate(String input) throws Exception {
        if (input.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return input;
        } else if (input.matches("\\d{1,2}")) {
            int day = Integer.parseInt(input);
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            cal.set(Calendar.DAY_OF_MONTH, day);
            return sdf.format(cal.getTime());
        } else {
            throw new Exception("Invalid date format");
        }
    }

    private void saveApplicationToDatabase(String location, String startDate, String endDate) {
        Connection conn = DatabaseConnection.connect();
        if (conn != null) {
            String sql = "INSERT INTO pending_applications (location, start_date, end_date) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, location);
                pstmt.setString(2, startDate);
                pstmt.setString(3, endDate);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Failed to save application: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new ApplicationsJF().setVisible(true);
        });
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
}