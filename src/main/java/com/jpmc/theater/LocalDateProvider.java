package com.jpmc.theater;

import java.time.LocalDate;

/**
 * LocalDateProvider singleton
 */
public class LocalDateProvider {
    // Add volatile to ensure visibility of changes across threads
    private volatile static LocalDateProvider instance = null;
    private LocalDateProvider() {}

    /**
     * Returns the singleton instance of this class.
     *
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
    
    /**
     * Returns the current local date.
     *
     * @return the current local date
     */
    public LocalDate currentDate() {
            return LocalDate.now();
    }
}
