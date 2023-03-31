package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MovieTests {
    @Test
    void specialMovieWith20PercentDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home","Dummy Description", Duration.ofMinutes(90),12.5, 1);
        // Make sure the only discount applied is the special code discount
        Showing showing = new Showing(spiderMan, 3, LocalDateTime.of(LocalDate.of(2023,3, 30), LocalTime.of(20,0)));
        assertEquals(12.5 * (1 - 0.2), spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void firstOfTheDayDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home","Dummy Description", Duration.ofMinutes(90),12.5, 0);
        // Make sure the only discount applied is the 1st sequence of the day discount
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.of(2023,3, 30), LocalTime.of(20,0)));
        assertEquals(12.5 - 3, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void secondOfTheDayDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home","Dummy Description", Duration.ofMinutes(90),12.5, 0);
        // Make sure the only discount applied is the 2ed sequence of the day discount
        Showing showing = new Showing(spiderMan, 2,LocalDateTime.of(LocalDate.of(2023,3, 30), LocalTime.of(20,0)));
        assertEquals(12.5 - 2, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void between11And4Discount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home","Dummy Description", Duration.ofMinutes(90),12.5, 0);
        // Make sure the only discount applied is the 11 a.m - 4 p.m discount
        Showing showing = new Showing(spiderMan, 3, LocalDateTime.of(LocalDate.of(2023,3, 30), LocalTime.of(13, 0)));
        assertEquals(12.5 * (1 - 0.25), spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void on7thDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home","Dummy Description", Duration.ofMinutes(90),12.5, 0);
        // Make sure the only discount applied is the on 7th discount
        Showing showing = new Showing(spiderMan, 3, LocalDateTime.of(LocalDate.of(2023,4,7), LocalTime.of(20, 0)));
        assertEquals(12.5 - 1, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void biggestDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home","Dummy Description", Duration.ofMinutes(90),12.5, 1);
        // When multiple discounts are qualified, use the biggest discount
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.of(2023,4,7), LocalTime.of(13, 0)));
        // 12.5 - 3 = 9.5
        // 12.5 - 2 = 10.5
        // 12.5 * (1 - 0.20) = 11.7
        // 12.5 * (1 - 0.25) = 9.375 (winner)
        // 12.5 - 1 = 11.5
        assertEquals(12.5 * (1 - 0.25), spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void equalsAndHashCode() {
        Movie movie1 = new Movie("Spider-Man: No Way Home","Dummy Description", Duration.ofMinutes(90),12.5, 0);
        Movie movie2 = new Movie("Spider-Man: No Way Home","Dummy Description", Duration.ofMinutes(90),12.5, 0);
        Movie movie3 = new Movie("Turning Red", "Dummy Description", Duration.ofMinutes(85), 11, 0);
        // Test equal
        assertTrue(movie1.equals(movie2));
        assertFalse(movie1.equals(movie3));
        // Test hashcode
        assertEquals(movie1.hashCode(), movie2.hashCode());
        assertNotEquals(movie1.hashCode(), movie3.hashCode());
    }
}
