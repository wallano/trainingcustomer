package co.com.bancolombia.clientes.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.bancolombia.clientes.dto.ClienteBancoDTO;
import co.com.bancolombia.clientes.entities.ClienteBanco;



public class ConvertClienteBanco {

	@Autowired
	private ModelMapper modelMapper;
	
	public ClienteBanco convertToEntity(ClienteBancoDTO clienteBancoDTO) {
		return modelMapper.map(clienteBancoDTO, ClienteBanco.class);
	}
	
	public ClienteBancoDTO convertToDTO(ClienteBanco clienteBanco) {
		return modelMapper.map(clienteBanco, ClienteBancoDTO.class);
	}

}
