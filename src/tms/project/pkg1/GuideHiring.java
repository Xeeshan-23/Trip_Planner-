/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tms.project.pkg1;

/**
 *
 * @author hp
 */
public class GuideHiring {
    private String name;
    private int experience;

    public GuideHiring(String name, int experience) {
        this.name = name;
        this.experience = experience;
    }

    public double calculateGuideFee(int totalMembers) {
        // Basic fee calculation (could be more complex)
        return totalMembers * 50.0; // Example: $50 per member
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }
}
