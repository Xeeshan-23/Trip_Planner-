package tms.project.pkg1;

import java.time.LocalDateTime;

public class Tour {
    private User user;
    private Location location;
    private CabBooking cabBooking;
    private HotelBooking hotelBooking;
    private GuideHiring guideHiring;
    private Bill bill;
    private int totalMembers;
    private LocalDateTime dateTime;

    public Tour(User user) {
        this.user = user;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     *
     * @param cam
     */
    public void setCabBooking(CabBooking pf) {
        this.cabBooking = cabBooking;
    }

    public void setHotelBooking(HotelBooking hotelBooking) {
        this.hotelBooking = hotelBooking;
    }

    public void setGuideHiring(GuideHiring guideHiring) {
        this.guideHiring = guideHiring;
    }

    public void setTotalMembers(int totalMembers) {
        this.totalMembers = totalMembers;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void generateBill() {
        if (cabBooking != null && hotelBooking != null && guideHiring != null) {
            double cabFare = cabBooking.calculateCabFare(totalMembers);
            double hotelCost = hotelBooking.calculateHotelCost(totalMembers);
            double guideFee = guideHiring.calculateGuideFee(totalMembers);
            
            bill = new Bill();
            bill.generateBill(cabFare, hotelCost, guideFee);
        } else {
            System.out.println("Complete all arrangements before generating the bill.");
        }
    }

    public Bill getBill() {
        return bill;
    }

    public User getUser() {
        return user;
    }

    // Added toString method for better output readability
    @Override
    public String toString() {
        return "Tour requested by: " + user.getName() + ", Location: " + (location != null ? location : "Not set") + ", DateTime: " + dateTime;
    }
}
