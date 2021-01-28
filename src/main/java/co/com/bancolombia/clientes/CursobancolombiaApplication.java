package co.com.bancolombia.clientes;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import co.com.bancolombia.clientes.dto.CuentaDTO;
import co.com.bancolombia.clientes.util.ConvertClienteBanco;
import co.com.bancolombia.clientes.util.ConvertCuenta;


@SpringBootApplication
public class CursobancolombiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursobancolombiaApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public CuentaDTO cuentaDTO() {
		return new CuentaDTO();
	}
	
	@Bean
	public ConvertCuenta convertCuenta() {
		return new ConvertCuenta();
	}
	
	@Bean
	public ConvertClienteBanco convertClienteBanco() {
		return new ConvertClienteBanco();
	}

}
