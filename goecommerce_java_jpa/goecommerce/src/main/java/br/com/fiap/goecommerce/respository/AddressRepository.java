package br.com.fiap.goecommerce.respository;

import br.com.fiap.goecommerce.entity.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address,Long> {
}
