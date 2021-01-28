package co.com.bancolombia.clientes.repositories;

import org.springframework.data.repository.CrudRepository;

import co.com.bancolombia.clientes.entities.Cuenta;

public interface CuentaRepository extends CrudRepository<Cuenta, Long> {

}
