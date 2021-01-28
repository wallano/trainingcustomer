package co.com.bancolombia.clientes.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.bancolombia.clientes.dto.CuentaDTO;
import co.com.bancolombia.clientes.entities.Cuenta;



public class ConvertCuenta {

	@Autowired
	private ModelMapper modelMapper;
	
	public Cuenta convertToEntity(CuentaDTO cuentaDTO) {
		return modelMapper.map(cuentaDTO, Cuenta.class);
	}
	
	public CuentaDTO convertToDTO(Cuenta cuenta) {
		return modelMapper.map(cuenta, CuentaDTO.class);
	}
		
}
