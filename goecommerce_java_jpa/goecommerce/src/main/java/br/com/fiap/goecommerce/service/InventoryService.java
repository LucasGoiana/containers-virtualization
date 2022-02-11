package br.com.fiap.goecommerce.service;

import br.com.fiap.goecommerce.dto.InventoryDTO;
import br.com.fiap.goecommerce.entity.Inventory;


import java.util.List;

public interface InventoryService {
    InventoryDTO getProductById(Long id);

    List<Inventory> saveAll(List<Inventory> inventories);

    InventoryDTO createInventory(InventoryDTO inventoryDTO);
}
