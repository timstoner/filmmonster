package com.example.filmmonster.service.mapper;

import com.example.filmmonster.domain.*;
import com.example.filmmonster.service.dto.CityDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity City and its DTO CityDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CityMapper {

    @Mapping(source = "country.id", target = "countryId")
    CityDTO cityToCityDTO(City city);

    List<CityDTO> citiesToCityDTOs(List<City> cities);

    @Mapping(source = "countryId", target = "country")
    City cityDTOToCity(CityDTO cityDTO);

    List<City> cityDTOsToCities(List<CityDTO> cityDTOs);

    default Country countryFromId(Long id) {
        if (id == null) {
            return null;
        }
        Country country = new Country();
        country.setId(id);
        return country;
    }
}
