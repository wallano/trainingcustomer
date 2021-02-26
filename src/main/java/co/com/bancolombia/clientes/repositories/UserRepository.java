package co.com.bancolombia.clientes.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.com.bancolombia.clientes.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByUsername(String username);
	
}
