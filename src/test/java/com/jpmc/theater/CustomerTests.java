package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTests {
    @Test
    public void equalsAndHashCode() {
        Customer customer1 = new Customer("John Doe", "123");
        Customer customer2 = new Customer("John Doe", "123");
        Customer customer3 = new Customer("John Doe", "124");
        Customer customer4 = new Customer("Indiana Jones", "456");
        // Test equals
        assertTrue(customer1.equals(customer2));
        assertFalse(customer1.equals(customer3));
        assertFalse(customer1.equals(customer4));
        // Test hashcode
        assertEquals(customer1.hashCode(), customer2.hashCode());
        assertNotEquals(customer2.hashCode(), customer3.hashCode());
    }

    @Test
    public void testToString() {
        Customer customer1 = new Customer("John Smith", "123");
        assertEquals("name: John Smith", customer1.toString());
    }
}
