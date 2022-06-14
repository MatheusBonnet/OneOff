package com.oneoff.aep.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oneoff.aep.data.UserDTO;
import com.oneoff.aep.entities.User;
import com.oneoff.aep.repositories.IUserRepository;
import com.oneoff.aep.services.IUserService;

/**
 * @author matheus
 *
 */

@RestController
@RequestMapping("v1/api/auth")
public class LoginController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private final PasswordEncoder encoder = null;

    
    @PostMapping("/singup")
    public ResponseEntity<User> salvar(@RequestBody UserDTO usuario) {
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        return ResponseEntity.ok(userService.saveUser(usuario));
    }
    
    

}
