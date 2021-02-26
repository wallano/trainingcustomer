package co.com.bancolombia.clientes.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.com.bancolombia.clientes.entities.UserToken;


public interface UserTokenRepository extends CrudRepository<UserToken, Long>{
	
	@Query("SELECT u FROM UserToken u WHERE u.username = ?1")
	public String obtenerToken(String username);

}
