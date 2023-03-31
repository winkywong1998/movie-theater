package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Represents a movie with a title, running time, ticket price, and special code
 */
public class Movie {
    private static int MOVIE_CODE_SPECIAL = 1;
    private String title;
    //The description here is never used
    private String description;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;

    /**
     * Constructs a new Movie object with the given title, description, running time, ticket price, and special code.
     *
     * @param title       movie title
     * @param description movie description
     * @param runningTime movie running time
     * @param ticketPrice movie ticket price
     * @param specialCode movie special code
     */
    public Movie(String title,String description, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.description = description;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    /**
     * Returns the title of the movie.
     * @return the title of the movie
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the running time of the movie.
     * @return the running time of the movie
     */
    public Duration getRunningTime() {
        return runningTime;
    }

    /**
     * Returns the ticket price of the movie.
     * @return the ticket price of the movie
     */
    public double getTicketPrice() {
        return ticketPrice;
    }

    /**
     * Calculates the ticket price for the given showing of the movie based on any applicable discounts.
     *
     * @param showing the showing of the movie for which to calculate the ticket price
     * @return the ticket price for the given showing of the movie
     */
    public double calculateTicketPrice(Showing showing) {
        return ticketPrice - getDiscount(showing);
    }

    /**
     * Returns the biggest discount applicable for the given showing of the movie.
     * @param showing the showing of the movie for which to get the discount
     * @return the biggest discount applicable for the given showing of the movie
     */
    private double getDiscount(Showing showing) {
        // Use a priority queue to keep track of the biggest discount
        Queue<Double> biggestDiscount = new PriorityQueue<>(Collections.reverseOrder());
        if (MOVIE_CODE_SPECIAL == specialCode) {
            biggestDiscount.offer(ticketPrice * 0.2);  // 20% discount for special movie
        }
        if (showing.getSequenceOfTheDay() == 1) {
            biggestDiscount.offer(3.0); // $3 discount for 1st show
        } else if (showing.getSequenceOfTheDay() == 2) {
            biggestDiscount.offer(2.0); // $2 discount for 2nd show
        }
        LocalDateTime dateTime = showing.getStartTime();
        if (isBetween(dateTime)){
            biggestDiscount.offer(ticketPrice * 0.25);// 25% discount 11AM ~ 4pm
        }
        if (onDate(dateTime, 7)){
            biggestDiscount.offer(1.0);//1$ discount 7th
        }
        // Biggest discount wins, if no discount applied, return 0
        return biggestDiscount.isEmpty() ? 0 : biggestDiscount.poll();
    }

    /**
     * Checks if the given date time falls within a specified time range (11AM-4PM).
     * @param dateTime the date and time to check
     * @return true if the time of the given date time falls within the specified range, false otherwise
     */
    private boolean isBetween(LocalDateTime dateTime){
        LocalTime time = dateTime.toLocalTime();
        LocalTime start = LocalTime.of(11, 0); // 11 AM
        LocalTime end = LocalTime.of(16, 0); // 4 PM
        // Use equal to check the including star and end hour
        return (time.equals(start) || time.isAfter(start)) && (time.equals(end) || time.isBefore(end));
    }

    /**
     * Checks if the given date time falls on the specified date.
     * @param dateTime the date and time to check
     * @param date the date to check for
     * @return true if the date of the given date time matches the specified date, false otherwise
     */
    private boolean onDate(LocalDateTime dateTime, int date) {
        return dateTime.getDayOfMonth() == date;
    }

    /**
     * Compares this movie to another object for equality.
     *
     * @param o the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    /**
     * Returns a hash code for this movie.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPrice, specialCode);
    }
}