package br.com.fiap.goecommerce.respository;

import br.com.fiap.goecommerce.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

}
