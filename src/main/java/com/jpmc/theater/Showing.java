package com.jpmc.theater;

import java.time.LocalDateTime;

/**
 * Represents a specific showing of a movie.
 */
public class Showing {
    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    /**
     * Constructs a new showing object with the given movie,
     * sequence of the day, and start time.
     *
     * @param movie            showing movie
     * @param sequenceOfTheDay sequence of the day for the showing
     * @param showStartTime    showing start time
     */
    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    /**
     * Returns the movie being shown.
     *
     * @return the movie being shown.
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * Returns the start time for the showing.
     *
     * @return the start time for the showing.
     */
    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    /**
     * Returns the original price of a movie
     *
     * @return the original price of a movie
     */
    public double getMovieFee() {
        return movie.getTicketPrice();
    }

    /**
     * Returns the sequence of the day for the showing.
     *
     * @return the sequence of the day for the showing.
     */
    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    /**
     * Calculates the total fee for the given audience count based on the
     * movie's ticket price and any discounts or fees.
     *
     * @param audienceCount count of audience
     * @return the total fee for the showing.
     */
    //Have to make it public so that the final fee can be obtained
    public double calculateFee(int audienceCount) {
        return movie.calculateTicketPrice(this) * audienceCount;
    }
}
