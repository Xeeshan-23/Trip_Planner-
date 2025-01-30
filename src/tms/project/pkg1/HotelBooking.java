/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tms.project.pkg1;

/**
 *
 * @author hp
 */
public class HotelBooking {
    private String city;
    private String checkInDate;
    private String checkOutDate;
    private String hotelType;

    public HotelBooking(String city, String checkInDate, String checkOutDate, String hotelType) {
        this.city = city;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.hotelType = hotelType;
    }

    public double calculateHotelCost(int totalMembers) {
        // Example cost calculation
        return totalMembers * 100.0; // Example: $100 per member per night
    }

    // Getters and Setters
    public String getCity() {
        return city;
    }

    public String getHotelType() {
        return hotelType;
    }
}