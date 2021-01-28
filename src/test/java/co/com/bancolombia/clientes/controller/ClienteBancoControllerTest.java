package co.com.bancolombia.clientes.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.bancolombia.clientes.CursobancolombiaApplication;
import co.com.bancolombia.clientes.dto.ClienteBancoDTO;
import co.com.bancolombia.clientes.dto.CuentaDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CursobancolombiaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteBancoControllerTest {
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@LocalServerPort
    private int port;
	
	private String getRootUrl() {
        return "http://localhost:" + port;
    }
	
	@Test
    public void adicionarClienteBancoTest() {
    	ClienteBancoDTO clienteBancoDTO = new ClienteBancoDTO();
    	CuentaDTO cuentaDTO = new CuentaDTO();
    	clienteBancoDTO.setCedula("123456789");
    	clienteBancoDTO.setPrimerNombre("Pepito");
    	clienteBancoDTO.setPrimerApellido("Perez");
    	cuentaDTO.setTipoCuenta("Ahorros");
    	cuentaDTO.setNumeroCuenta("987654321");
    	clienteBancoDTO.setCuentaDTO(cuentaDTO);
    	
    	HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ClienteBancoDTO> entity = new HttpEntity<ClienteBancoDTO>(clienteBancoDTO, headers);
		ResponseEntity<String> postResponse = restTemplate.exchange(getRootUrl() + "/clientes/adicionarCliente", HttpMethod.POST, entity, String.class);		
    	//ResponseEntity<ClubFutbolDTO> postResponse = restTemplate.postForEntity(getRootUrl() + "/clubes/adicionarClub", clubFutbolDTO, ClubFutbolDTO.class);
    	assertEquals(200, postResponse.getStatusCode().value());
        //assertNotNull(postResponse);
        //assertNotNull(postResponse.getBody());*/
    	
    }

}
