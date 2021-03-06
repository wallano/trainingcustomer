package co.com.bancolombia.clientes.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import co.com.bancolombia.clientes.dto.AutenticationRequestDTO;
import co.com.bancolombia.clientes.dto.AutenticationResponseDTO;
import co.com.bancolombia.clientes.dto.ClienteBancoDTO;
import co.com.bancolombia.clientes.dto.CuentaDTO;
import co.com.bancolombia.clientes.entities.ClienteBanco;
import co.com.bancolombia.clientes.entities.UserToken;
import co.com.bancolombia.clientes.exception.ResourceNotFoundException;
import co.com.bancolombia.clientes.repositories.ClienteBancoRepository;
import co.com.bancolombia.clientes.repositories.UserTokenRepository;
import co.com.bancolombia.clientes.util.Constantes;
import co.com.bancolombia.clientes.util.ConvertClienteBanco;
import co.com.bancolombia.clientes.util.ConvertCuenta;

@RestController
public class ClienteBancoController {
	
	@Autowired
	private ClienteBancoRepository clienteBancoRepository;
	
	@Autowired
	private ConvertClienteBanco convertClienteBanco;
	
	@Autowired
	private ConvertCuenta convertCuenta;
	
	@Autowired
	UserToken userToken;
	
	@Autowired
    private RestTemplate restTemplate;
	
	@Autowired
	UserTokenRepository userTokenRepository;
	
	@PostMapping("/autenticar")
	public String autenticar(@RequestBody AutenticationRequestDTO autenticationRequestDTO) {
		String baseUrl= "http://localhost:8080";	
		baseUrl=baseUrl+"/auth/signin";
		ResponseEntity<String> postResponse = restTemplate.postForEntity(baseUrl, autenticationRequestDTO, String.class);
		System.out.println("Respuesta: "+postResponse.getBody());
		Gson g = new Gson();
		AutenticationResponseDTO autenticationResponseDTO = g.fromJson(postResponse.getBody(), AutenticationResponseDTO.class);
		userToken.setUsername(autenticationResponseDTO.getUsername());
		userToken.setToken(autenticationResponseDTO.getToken());
		userTokenRepository.save(userToken);
		return autenticationResponseDTO.getToken();
		
	}
	
	@PostMapping("/clientes/adicionarCliente")
	public Map<String, String> adicionarCliente(@RequestBody ClienteBancoDTO clienteBancoDTO){
		
		Map<String, String> response = new HashMap<>();
		try {
			ClienteBanco clienteBanco = convertClienteBanco.convertToEntity(clienteBancoDTO);
			clienteBancoRepository.save(clienteBanco);
			response.put(Constantes.CODIGO_HTTP, "200");
			response.put(Constantes.MENSAJE_EXITO, "Registrado insertado exitosamente");
			return response;
		} catch (Exception e) {
			response.put(Constantes.CODIGO_HTTP, "500");
			response.put(Constantes.MENSAJE_ERROR, "Ocurrió un problema al insertar");
			return response;
		}
		
	}
	
	@GetMapping("/clientes")
	public List<ClienteBancoDTO> obtenerClientes(){
		Iterable<ClienteBanco> iClientesBanco = clienteBancoRepository.findAll();
		List<ClienteBanco> listaClientesBanco = new ArrayList<ClienteBanco>();
		List<ClienteBancoDTO> listaClientesBancoDTO = new ArrayList<ClienteBancoDTO>();
		iClientesBanco.iterator().forEachRemaining(listaClientesBanco::add);
		for(int i = 0; i < listaClientesBanco.size(); i++) {
			try {
				CuentaDTO cuentaDTO = null;
				if(listaClientesBanco.get(i).getCuenta() != null) {
					cuentaDTO = convertCuenta.convertToDTO(listaClientesBanco.get(i).getCuenta());
				} 
				ClienteBancoDTO clienteBancoDTO = convertClienteBanco.convertToDTO(listaClientesBanco.get(i));
				clienteBancoDTO.setCuentaDTO(cuentaDTO);
				listaClientesBancoDTO.add(clienteBancoDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return listaClientesBancoDTO;
	}
	
	@DeleteMapping("/clientes/{id}")
	public Map<String, String> deleteCliente(@PathVariable(value = "id") Long clienteId)
			throws ResourceNotFoundException {
		ClienteBanco cliente = clienteBancoRepository.findById(clienteId)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado para este id :: " + clienteId));

		clienteBancoRepository.delete(cliente);
		Map<String, String> response = new HashMap<>();
		response.put(Constantes.CODIGO_HTTP, "200");
		response.put(Constantes.MENSAJE_ERROR, "Registrado borrado exitosamente");
		return response;
	}
	

}
