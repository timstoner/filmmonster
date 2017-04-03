package com.example.filmmonster.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Rental entity.
 */
public class RentalDTO implements Serializable {

    private Long id;

    private ZonedDateTime rentalDate;

    private ZonedDateTime returnDate;

    private ZonedDateTime lastUpdate;


    private Long customerId;
    
    private Long inventoryId;
    
    private Long staffId;
    
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RentalDTO rentalDTO = (RentalDTO) o;

        if ( ! Objects.equals(id, rentalDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "RentalDTO{" +
            "id=" + id +
            ", rentalDate='" + rentalDate + "'" +
            ", returnDate='" + returnDate + "'" +
            ", lastUpdate='" + lastUpdate + "'" +
            '}';
    }
}
