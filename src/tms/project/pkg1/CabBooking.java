/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tms.project.pkg1;

/**
 *
 * @author hp
 */
public class CabBooking {
    private String pickupPlace;
    private String destination;
    private String date;
    private String time;

    public CabBooking(String pickupPlace, String destination) {
        this.pickupPlace = pickupPlace;
        this.destination = destination;
        this.date = date;
        this.time = time;
    }

    public double calculateCabFare(int totalMembers) {
        // Basic fare calculation (could be more complex)
        return totalMembers * 10.0; // Example: $10 per member
    }

    // Getters and Setters
    public String getPickupPlace() {
        return pickupPlace;
    }

    public String getDestination() {
        return destination;
    }
}
