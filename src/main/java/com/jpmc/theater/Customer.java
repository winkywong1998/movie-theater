package com.jpmc.theater;

import java.util.Objects;

/**
 * Represents a customer with a name and ID.
 */
public class Customer {
    private String name;
    private String id;
    /**
     * Constructs a new customer with the given name and ID.
     *
     * @param name customer name
     * @param id   customer id
     */
    public Customer(String name, String id) {
        this.id = id; // NOTE - id is not used anywhere at the moment
        this.name = name;
    }

    /**
     * Compares this customer to another object for equality.
     *
     * @param o the object to compare to
     * @return  true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(id, customer.id);
    }

    /**
     * Returns a hash code for this customer.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    /**
     * Returns a string representation of this customer by name only.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "name: " + name;
    }
}