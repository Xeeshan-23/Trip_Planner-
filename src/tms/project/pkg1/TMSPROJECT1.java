package tms.project.pkg1;

public class TMSPROJECT1 {
    public static void main(String[] args) {
        // Show the Login JFrame
        java.awt.EventQueue.invokeLater(() -> {
            Login loginFrame = new Login();
            loginFrame.setVisible(true);
        });

        // Other logic can follow later after UI initialization
        User user = new User("John Doe", "john@example.com", "password123");
        user.register();

        // User requests a tour
        Tour tour = user.requestTour();

        // Set location
        Location location = new Location("New York", "Los Angeles");
        tour.setLocation(location);

        // Set cab booking
        CabBooking cabBooking = new CabBooking("NY", "LA");
        tour.setCabBooking(cabBooking);

        // Set hotel booking
        HotelBooking hotelBooking = new HotelBooking("Los Angeles", "2024-05-01", "2024-05-05", "Luxury");
        tour.setHotelBooking(hotelBooking);

        // Set guide hiring
        GuideHiring guideHiring = new GuideHiring("Alice", 5);
        tour.setGuideHiring(guideHiring);

        // Set total members and date/time
        tour.setTotalMembers(3);
        tour.setDateTime(java.time.LocalDateTime.of(2024, 5, 1, 10, 0));

        // Admin arranges the tour
//        Admin admin = new Admin();
//        admin.manageTour(tour);
//        admin.arrangeTour(tour);

        // Generate the bill
        tour.generateBill();
        Bill bill = tour.getBill();
        System.out.println("Bill total: $" + bill.getTotalAmount());
        bill.payBill();
    }
}
