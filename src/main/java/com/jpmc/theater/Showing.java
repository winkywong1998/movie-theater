package com.jpmc.theater;

import java.time.LocalDateTime;

public class Showing {
    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    //The isSequence() method is confusing and duplicated
//    public boolean isSequence(int sequence) {
//        return this.sequenceOfTheDay == sequence;
//    }

    public double getMovieFee() {
        return movie.getTicketPrice();
    }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    //Have to make it public so that the final fee can be obtained
    public double calculateFee(int audienceCount) {
        return movie.calculateTicketPrice(this) * audienceCount;
    }
}
