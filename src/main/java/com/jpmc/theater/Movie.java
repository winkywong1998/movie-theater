package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class Movie {
    private static int MOVIE_CODE_SPECIAL = 1;

    private String title;
    private String description;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;

    public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public double calculateTicketPrice(Showing showing) {
        return ticketPrice - getDiscount(showing);
    }

    private double getDiscount(Showing showing) {
        double specialDiscount = 0;
        if (MOVIE_CODE_SPECIAL == specialCode) {
            specialDiscount = ticketPrice * 0.2;  // 20% discount for special movie
        }
        double sequenceDiscount = 0;
        if (showing.getSequenceOfTheDay() == 1) {
            sequenceDiscount = 3; // $3 discount for 1st show
        } else if (showing.getSequenceOfTheDay() == 2) {
            sequenceDiscount = 2; // $2 discount for 2nd show
        }
        LocalDateTime dateTime = showing.getStartTime();
        double timeDiscount = 0;
        if (isBetween(dateTime)){
            timeDiscount = ticketPrice * 0.25;// 25% discount 11AM ~ 4pm
        }
        double dateDiscount = 0;
        if (onDate(dateTime, 7)){
            dateDiscount = 1.0;//1$ discount 7th
        }
//        else {
//            throw new IllegalArgumentException("failed exception");
//        }

        // biggest discount wins
        return specialDiscount > sequenceDiscount ? specialDiscount : sequenceDiscount;
    }

    private boolean isBetween(LocalDateTime dateTime){
        LocalTime time = dateTime.toLocalTime();
        LocalTime start = LocalTime.of(11, 0); // 11 AM
        LocalTime end = LocalTime.of(16, 0); // 4 PM
        //Use equal to check the including star and end hour
        return (time.equals(start) || time.isAfter(start)) && (time.equals(end) || time.isBefore(end));
    }

    private boolean onDate(LocalDateTime dateTime, int date) {
        return dateTime.getDayOfMonth() == date;
    }


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

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPrice, specialCode);
    }
}