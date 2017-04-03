package com.example.filmmonster.service.mapper;

import com.example.filmmonster.domain.*;
import com.example.filmmonster.service.dto.InventoryDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Inventory and its DTO InventoryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InventoryMapper {

    @Mapping(source = "film.id", target = "filmId")
    @Mapping(source = "store.id", target = "storeId")
    InventoryDTO inventoryToInventoryDTO(Inventory inventory);

    List<InventoryDTO> inventoriesToInventoryDTOs(List<Inventory> inventories);

    @Mapping(source = "filmId", target = "film")
    @Mapping(source = "storeId", target = "store")
    Inventory inventoryDTOToInventory(InventoryDTO inventoryDTO);

    List<Inventory> inventoryDTOsToInventories(List<InventoryDTO> inventoryDTOs);

    default Film filmFromId(Long id) {
        if (id == null) {
            return null;
        }
        Film film = new Film();
        film.setId(id);
        return film;
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
