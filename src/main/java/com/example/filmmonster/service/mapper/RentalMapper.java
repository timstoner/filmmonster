package com.example.filmmonster.service.mapper;

import com.example.filmmonster.domain.*;
import com.example.filmmonster.service.dto.RentalDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Rental and its DTO RentalDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RentalMapper {

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "inventory.id", target = "inventoryId")
    @Mapping(source = "staff.id", target = "staffId")
    RentalDTO rentalToRentalDTO(Rental rental);

    List<RentalDTO> rentalsToRentalDTOs(List<Rental> rentals);

    @Mapping(source = "customerId", target = "customer")
    @Mapping(source = "inventoryId", target = "inventory")
    @Mapping(source = "staffId", target = "staff")
    Rental rentalDTOToRental(RentalDTO rentalDTO);

    List<Rental> rentalDTOsToRentals(List<RentalDTO> rentalDTOs);

    default Customer customerFromId(Long id) {
        if (id == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(id);
        return customer;
    }

    default Inventory inventoryFromId(Long id) {
        if (id == null) {
            return null;
        }
        Inventory inventory = new Inventory();
        inventory.setId(id);
        return inventory;
    }

    default Staff staffFromId(Long id) {
        if (id == null) {
            return null;
        }
        Staff staff = new Staff();
        staff.setId(id);
        return staff;
    }
}
