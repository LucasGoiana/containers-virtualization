package br.com.fiap.goecommerce.respository;

import br.com.fiap.goecommerce.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Client,Long> {
}
