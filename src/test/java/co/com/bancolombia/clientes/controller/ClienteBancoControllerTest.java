package co.com.bancolombia.clientes.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import co.com.bancolombia.clientes.CursobancolombiaApplication;
import co.com.bancolombia.clientes.dto.ClienteBancoDTO;
import co.com.bancolombia.clientes.dto.CuentaDTO;
import co.com.bancolombia.clientes.entities.ClienteBanco;


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
    	clienteBancoDTO.setCedula("716543789");
    	clienteBancoDTO.setPrimerNombre("Juanchito");
    	clienteBancoDTO.setPrimerApellido("Arias");
    	cuentaDTO.setTipoCuenta("Corriente");
    	cuentaDTO.setNumeroCuenta("0981234567");
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
	
	@Test
    public void testDeleteCliente() {
         int id = 1;
         ClienteBanco clienteBanco = restTemplate.getForObject(getRootUrl() + "/clientes/" + id, ClienteBanco.class);
         assertNotNull(clienteBanco);
         restTemplate.delete(getRootUrl() + "/clientes/" + id);
         try {
        	 clienteBanco = restTemplate.getForObject(getRootUrl() + "/clientes/" + id, ClienteBanco.class);
         } catch (final HttpClientErrorException e) {
              assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
         }
    }

}
