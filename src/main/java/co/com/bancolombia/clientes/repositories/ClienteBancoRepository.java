package co.com.bancolombia.clientes.repositories;

import org.springframework.data.repository.CrudRepository;

import co.com.bancolombia.clientes.entities.ClienteBanco;

public interface ClienteBancoRepository extends CrudRepository<ClienteBanco, Long> {

}
