package com.jpmc.theater;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Represents a movie theater that allows customers to reserve seats for a movie showing.
 */
public class Theater {

    LocalDateProvider provider;
    private List<Showing> schedule;

    public Theater(LocalDateProvider provider) {
        this.provider = provider;
        // Since the movie is now hard coded and the description is not used, use "Dummy Description" to Represent
        Movie spiderMan = new Movie("Spider-Man: No Way Home","Dummy Description", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", "Dummy Description", Duration.ofMinutes(85), 11, 0);
        Movie theBatMan = new Movie("The Batman","Dummy Description",  Duration.ofMinutes(95), 9, 0);
        schedule = List.of(
            new Showing(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0))),
            new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))),
            new Showing(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))),
            new Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))),
            new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))),
            new Showing(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))),
            new Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))),
            new Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))),
            new Showing(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0)))
        );
    }

    /**
     * Reserves seats for a showing with the given sequence number and number of tickets for a customer.
     * @param customer       the customer making the reservation
     * @param sequence       the sequence number of the showing to reserve seats for
     * @param howManyTickets the number of seats to reserve
     * @return the Reservation object representing the reserved seats.
     * @throws IndexOutOfBoundsException if the sequence number is invalid or out of bounds.
     */
    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        Showing showing;
        try {
            showing = schedule.get(sequence - 1);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            // IllegalStateException means a method has been invoked at the wrong time
            // IndexOutOfBoundsException will be more reasonable since it's input invalidation
            throw new IndexOutOfBoundsException("Not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, showing, howManyTickets);
    }

    /**
     * Prints the movie schedule to standard output in a human-readable format.
     */
    public void printSchedule() {
        System.out.println(provider.currentDate());
        System.out.println("===================================================");
        schedule.forEach(s ->
                System.out.println(s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " " + humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovieFee())
        );
        System.out.println("===================================================");
    }

    /**
     * Prints the movie schedule to standard output in JSON format.
     */
    public void printScheduleJSON() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(schedule));
    }

    /**
     * Prints the movie schedule to standard output in JSON format with human-readable running time.
     */
    public void printScheduleJSONReadable() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Map<String, Object>> scheduleList = new ArrayList<>();
        for (Showing showing : schedule) {
            Map<String, Object> showingMap = new HashMap<>();
            showingMap.put("sequence", showing.getSequenceOfTheDay());
            showingMap.put("startTime", showing.getStartTime().toString());
            showingMap.put("movieTitle", showing.getMovie().getTitle());
            showingMap.put("runningTime", humanReadableFormat(showing.getMovie().getRunningTime()));
            showingMap.put("movieFee", showing.getMovieFee());
            scheduleList.add(showingMap);
        }
        System.out.println(gson.toJson(scheduleList));
    }

    /**
     * Formats the duration in human-readable format with hours and minutes.
     * If the duration is 1 hour and 1 minute, it would be formatted as (1 hour 1 minute).
     *
     * @param duration the duration to be formatted
     * @return the formatted string
     */
    public String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    /**
     * Helper method to handle plural suffix for a given value.
     * @param value the value to check
     * @return the plural suffix
     */
    // (s) postfix should be added to handle plural correctly
    private String handlePlural(long value) {
        if (value == 1) {
            return "";
        }
        else {
            return "s";
        }
    }

    public static void main(String[] args) {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedule();
        theater.printScheduleJSON();
        theater.printScheduleJSONReadable();
    }
}
