package com.oneoff.aep.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oneoff.aep.entities.User;
import com.oneoff.aep.model.Response;
import com.oneoff.aep.repositories.IUserRepository;
import com.oneoff.aep.services.IUserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	public static final String UPDATE = "Atualizado com sucesso.";

	public static final String DELETE = "Deletado com sucesso";
	
	@Autowired
	private IUserService userService;
		
	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}


    private final IUserRepository repository;
    
    private final PasswordEncoder encoder;

    public UserController(IUserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<User>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }


    @GetMapping("/validarSenha")
    public ResponseEntity<Boolean> validarSenha(@RequestParam String cpf,
                                                @RequestParam String password) {

        User optUsuario = repository.findByCpf(cpf);
        if (optUsuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        User usuario = optUsuario;
        boolean valid = encoder.matches(password, usuario.getPassword());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(valid);
    }
    
    @PutMapping("/updateUser")
	public ResponseEntity<Response<User>> atualizarUser(@RequestBody User user){
		Response<User> response = new Response<>();
		user.setPassword(encoder.encode(user.getPassword()));
		response.setData(this.getUserService().atualizarUsuario(user));
		response.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
    
    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(repository.findById(id));
    }
}
