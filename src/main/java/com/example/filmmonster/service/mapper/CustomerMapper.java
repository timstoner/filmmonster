package com.example.filmmonster.service.mapper;

import com.example.filmmonster.domain.*;
import com.example.filmmonster.service.dto.CustomerDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Customer and its DTO CustomerDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CustomerMapper {

    @Mapping(source = "address.id", target = "addressId")
    @Mapping(source = "store.id", target = "storeId")
    CustomerDTO customerToCustomerDTO(Customer customer);

    List<CustomerDTO> customersToCustomerDTOs(List<Customer> customers);

    @Mapping(source = "addressId", target = "address")
    @Mapping(source = "storeId", target = "store")
    Customer customerDTOToCustomer(CustomerDTO customerDTO);

    List<Customer> customerDTOsToCustomers(List<CustomerDTO> customerDTOs);

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
