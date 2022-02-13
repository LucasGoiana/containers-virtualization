package br.com.fiap.goecommerce.respository;

import br.com.fiap.goecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
