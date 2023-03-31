package com.jpmc.theater;

/**
 * Represents a reservation for a movie showing by a customer.
 */
public class Reservation {
    private Customer customer;
    private Showing showing;
    private int audienceCount;

    /**
     * Creates a new reservation object for the given customer and showing with the specified audience count.
     *
     * @param customer      the customer making reservation.
     * @param showing       the showing for which the reservation is being made.
     * @param audienceCount the number of people in the audience for the reservation.
     */
    public Reservation(Customer customer, Showing showing, int audienceCount) {
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;
    }

    /**
     * Calculates the total fee for the reservation based on the showing's fee calculation and the audience count.
     *
     * @return the total fee for the reservation based on audienceCount.
     */
    public double totalFee() {
        // TotalFee should return the final price after discount instead of original price
        return showing.calculateFee(audienceCount);
    }
}