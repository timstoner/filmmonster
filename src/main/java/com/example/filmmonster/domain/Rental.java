package com.example.filmmonster.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Rental.
 */
@Entity
@Table(name = "rental")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "rental")
public class Rental implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "rental_date")
    private ZonedDateTime rentalDate;

    @Column(name = "return_date")
    private ZonedDateTime returnDate;

    @Column(name = "last_update")
    private ZonedDateTime lastUpdate;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Inventory inventory;

    @ManyToOne
    private Staff staff;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(ZonedDateTime rentalDate) {
        this.rentalDate = rentalDate;
    }

    public ZonedDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(ZonedDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public ZonedDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(ZonedDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rental rental = (Rental) o;
        if(rental.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, rental.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Rental{" +
            "id=" + id +
            ", rentalDate='" + rentalDate + "'" +
            ", returnDate='" + returnDate + "'" +
            ", lastUpdate='" + lastUpdate + "'" +
            '}';
    }
}
