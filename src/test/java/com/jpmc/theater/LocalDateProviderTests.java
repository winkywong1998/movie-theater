package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class LocalDateProviderTests {
    @Test
    void makeSureCurrentTime() {
        System.out.println("current time is - " + LocalDateProvider.singleton().currentDate());
    }

    @Test
    public void testCurrentDate() {
        LocalDateProvider localDateProvider = LocalDateProvider.singleton();
        LocalDate currentDate = localDateProvider.currentDate();
        assertEquals(LocalDate.now(), currentDate);
    }

    @Test
    public void testCurrentHour() {
        LocalDateProvider localDateProvider = LocalDateProvider.singleton();
        LocalTime currentHour = LocalTime.now();
        assertEquals(currentHour.getHour(), localDateProvider.currentDate().atTime(currentHour).getHour());
    }

    @Test
    public void testSingleton() {
        LocalDateProvider instance1 = LocalDateProvider.singleton();
        LocalDateProvider instance2 = LocalDateProvider.singleton();
        assertSame(instance1, instance2);
    }
}
