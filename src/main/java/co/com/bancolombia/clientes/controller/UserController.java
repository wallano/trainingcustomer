package co.com.bancolombia.clientes.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.bancolombia.clientes.entities.User;
import co.com.bancolombia.clientes.repositories.UserRepository;
import co.com.bancolombia.clientes.util.Constantes;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	@PostMapping(path = "/users/addUser", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> adicionarUsuario(@RequestBody User user) {
		Map<String, String> response = new HashMap<>();
		User userInstance = new User();
		userInstance.setUsername(user.getUsername());
		userInstance.setPassword(this.passwordEncoder.encode(user.getPassword()));
		userInstance.setRoles(Arrays.asList( "ROLE_USER"));
		userRepository.save(userInstance);
		/*userRepository.save(User.builder()
		        .username(user.getUsername())
		        .password(this.passwordEncoder.encode(user.getPassword()))
		        .roles(Arrays.asList( "ROLE_USER"))
		        .build());*/
		response.put(Constantes.CODIGO_HTTP, "200");
		response.put(Constantes.MENSAJE_EXITO, "Registrado insertado exitosamente");
		return response;
		
	}
	
	@GetMapping("/users")
	public Iterable<User> listarUsuarios() {
		
		return userRepository.findAll();
		
	}

}
