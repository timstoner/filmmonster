package com.example.filmmonster.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Store entity.
 */
public class StoreDTO implements Serializable {

    private Long id;

    private ZonedDateTime lastUpdate;


    private Long addressId;
    
    private Long managerStaffId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public ZonedDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(ZonedDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getManagerStaffId() {
        return managerStaffId;
    }

    public void setManagerStaffId(Long staffId) {
        this.managerStaffId = staffId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StoreDTO storeDTO = (StoreDTO) o;

        if ( ! Objects.equals(id, storeDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "StoreDTO{" +
            "id=" + id +
            ", lastUpdate='" + lastUpdate + "'" +
            '}';
    }
}
