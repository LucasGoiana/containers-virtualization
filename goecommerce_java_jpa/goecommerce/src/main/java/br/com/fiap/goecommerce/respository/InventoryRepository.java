package br.com.fiap.goecommerce.respository;

import br.com.fiap.goecommerce.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
