package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {

    @Test
    void totalFee() {
        var customer = new Customer("John Doe", "unused-id");
        Movie spiderMan = new Movie("Spider-Man: No Way Home","Dummy Description", Duration.ofMinutes(90),12.5, 0);
        //Only discount applied is the 1st sequence of the day discount. 12.5 - 3 = 9.5
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.of(2023,3, 30), LocalTime.of(10,0)));
        assertTrue(new Reservation(customer, showing, 3).totalFee() == 9.5 * 3);
    }
}
