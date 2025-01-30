/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tms.project.pkg1;

/**
 *
 * @author hp
 */
public class Location {
    private String source;
    private String destination;

    public Location(String source, String destination) {
        this.source = source;
        this.destination = destination;
    }

    public void selectLocation(String source, String destination) {
        this.source = source;
        this.destination = destination;
        System.out.println("Location selected: " + source + " to " + destination);
    }

    // Getters and Setters
    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }
}
