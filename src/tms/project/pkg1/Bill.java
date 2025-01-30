/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tms.project.pkg1;

/**
 *
 * @author hp
 */
public class Bill {
    private double totalAmount;
    private boolean isPaid;

    public Bill() {
        this.isPaid = false;
    }

    public void generateBill(double cabFare, double hotelCost, double guideFee) {
        this.totalAmount = cabFare + hotelCost + guideFee;
        System.out.println("Bill generated. Total amount: $" + totalAmount);
    }

    public void payBill() {
        this.isPaid = true;
        System.out.println("Bill paid. Status: " + (isPaid ? "Paid" : "Unpaid"));
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public boolean isPaid() {
        return isPaid;
    }
}
