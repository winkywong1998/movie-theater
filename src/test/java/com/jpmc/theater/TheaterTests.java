package com.jpmc.theater;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheaterTests {
    @Test
    void totalFeeForCustomer() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 2, 4);
//        System.out.println("You have to pay " + reservation.getTotalFee());
        assertEquals(reservation.totalFee(), (12.5 *(1 - 0.25)) * 4);
    }

    @Test
    void printMovieSchedule() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedule();
    }

    @Test
    void printMovieScheduleJSON() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printScheduleJSON();
    }

    @Test
    void printMovieScheduleJSONReadable() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printScheduleJSONReadable();
    }

    @Test
    void testReserveInvalidSequence() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer customer = new Customer("John Doe", "id-12345");
        int invalidSequence = 0;
        int howManyTickets = 2;
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            theater.reserve(customer, invalidSequence, howManyTickets);
        });
    }
}
