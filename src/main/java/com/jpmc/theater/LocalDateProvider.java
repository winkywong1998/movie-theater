package com.jpmc.theater;

import java.time.LocalDate;

public class LocalDateProvider {
    // Add volatile to ensure visibility of changes across threads
    private volatile static LocalDateProvider instance = null;

    private LocalDateProvider() {}

    /**
     * @return make sure to return singleton instance
     */
    public static LocalDateProvider singleton() {
        if (instance == null) {
            // Make it thread safe
            synchronized (LocalDateProvider.class) {
                if (instance == null) {
                    instance = new LocalDateProvider();
                }
            }
        }
        return instance;
    }

    public LocalDate currentDate() {
            return LocalDate.now();
    }
}
