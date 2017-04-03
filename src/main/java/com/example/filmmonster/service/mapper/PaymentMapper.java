package com.example.filmmonster.service.mapper;

import com.example.filmmonster.domain.*;
import com.example.filmmonster.service.dto.PaymentDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Payment and its DTO PaymentDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PaymentMapper {

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "rental.id", target = "rentalId")
    @Mapping(source = "staff.id", target = "staffId")
    PaymentDTO paymentToPaymentDTO(Payment payment);

    List<PaymentDTO> paymentsToPaymentDTOs(List<Payment> payments);

    @Mapping(source = "customerId", target = "customer")
    @Mapping(source = "rentalId", target = "rental")
    @Mapping(source = "staffId", target = "staff")
    Payment paymentDTOToPayment(PaymentDTO paymentDTO);

    List<Payment> paymentDTOsToPayments(List<PaymentDTO> paymentDTOs);

    default Customer customerFromId(Long id) {
        if (id == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(id);
        return customer;
    }

    default Rental rentalFromId(Long id) {
        if (id == null) {
            return null;
        }
        Rental rental = new Rental();
        rental.setId(id);
        return rental;
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
