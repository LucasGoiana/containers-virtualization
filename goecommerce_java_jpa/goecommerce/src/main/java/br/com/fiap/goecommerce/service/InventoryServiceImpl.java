package br.com.fiap.goecommerce.service;

import br.com.fiap.goecommerce.configuration.exception.custom.ObjectNotFoundException;
import br.com.fiap.goecommerce.dto.InventoryDTO;
import br.com.fiap.goecommerce.entity.Inventory;
import br.com.fiap.goecommerce.respository.InventoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;


    public InventoryServiceImpl(InventoryRepository inventoryRepository, ModelMapper modelMapper) {
        this.inventoryRepository = inventoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public InventoryDTO getProductById(Long id) {
        return modelMapper.map(getInventoryEntityById(id), InventoryDTO.class);
    }

    @Override
    public List<Inventory> saveAll(List<Inventory> inventories) {
        return inventoryRepository.saveAll(inventories);
    }

    @Override
    public InventoryDTO createInventory(InventoryDTO inventoryDTO) {
        var inventory = modelMapper.map(inventoryDTO, Inventory.class);


        var inventoryNew = inventoryRepository.save(inventory);
        return modelMapper.map(inventoryNew, InventoryDTO.class);
    }


    private Inventory getInventoryEntityById(Long id) {
        return inventoryRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Inventario não encontrado!"));
    }

}
