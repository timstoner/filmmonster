package com.example.filmmonster.service.mapper;

import com.example.filmmonster.domain.*;
import com.example.filmmonster.service.dto.StaffDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Staff and its DTO StaffDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StaffMapper {

    @Mapping(source = "address.id", target = "addressId")
    @Mapping(source = "store.id", target = "storeId")
    StaffDTO staffToStaffDTO(Staff staff);

    List<StaffDTO> staffToStaffDTOs(List<Staff> staff);

    @Mapping(source = "addressId", target = "address")
    @Mapping(source = "storeId", target = "store")
    Staff staffDTOToStaff(StaffDTO staffDTO);

    List<Staff> staffDTOsToStaff(List<StaffDTO> staffDTOs);

    default Address addressFromId(Long id) {
        if (id == null) {
            return null;
        }
        Address address = new Address();
        address.setId(id);
        return address;
    }

    default Store storeFromId(Long id) {
        if (id == null) {
            return null;
        }
        Store store = new Store();
        store.setId(id);
        return store;
    }
}
