package com.oneoff.aep.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.oneoff.aep.DTO.UserDTO;
import com.oneoff.aep.entities.User;
import com.oneoff.aep.exception.UserException;
import com.oneoff.aep.facade.UserFacade;
import com.oneoff.aep.repositories.IUserRepository;


@Service
@Component
public class UserServiceImpl implements IUserService,UserDetailsService {

	public static final String MESSAGE_ERROR = "Erro interno no servidor, consulte o suporte!!!";
	
	public static final String MESSAGE_ERROR_USER_NOT_FOUND = "Usuario nao encontrado, tente novamente.";
	
	public static final String MESSAGE_ERROR_REGISTER_USER = "Erro ao salvar, tente novamente!";

	
	@Autowired
	private IUserRepository usuarioRepository;
	
	private ModelMapper mapper;
	
	@Autowired
	private UserFacade userfacade;
	
	@Autowired
	public UserServiceImpl(IUserRepository usuarioRepository) {
		this.mapper = new ModelMapper();
		this.usuarioRepository = usuarioRepository;
	}


	@Override
	public UserDTO deletar(Long id) throws UserException{
		try {
			this.buscaPorId(id);
			this.usuarioRepository.deleteById(id);
			return null;
						
		}catch (UserException m) {
			throw new UserException(MESSAGE_ERROR_USER_NOT_FOUND,HttpStatus.NOT_FOUND);
		}
	}


	@Override
	public User buscaPorId(Long id) throws UserException{
		try {
			Optional<User> daodorOptional = this.usuarioRepository.findById(id);
			if (daodorOptional.isPresent()) {
				return this.mapper.map(daodorOptional.get(), User.class);
			}
			throw new UserException(MESSAGE_ERROR_USER_NOT_FOUND, HttpStatus.NOT_FOUND);
		} catch (UserException m) {
			throw m;
		} catch (Exception e) {
			throw new UserException(MESSAGE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public User atualizarUsuario(User usuario) throws UserException{
		try {
			return userfacade.updateDataUser(usuario);
		} catch (UserException c) {
			throw new UserException(MESSAGE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		
	@Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        User usuario = usuarioRepository.findByCpf(cpf);

		if(usuario == null){
			throw new UsernameNotFoundException("Usuario não encontrado!");
		}
		return new org.springframework.security.core.userdetails.User(usuario.getCpf(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());

	}


	@Override
	public User saveUser(UserDTO user) throws UserException {
		try {
			User userSalvo = userfacade.registerUser(user);
			return userSalvo;
		} catch (UserException c) {
			throw new UserException(MESSAGE_ERROR_REGISTER_USER, HttpStatus.BAD_REQUEST);
		}
	}


	@Override
	public User findAuth() throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User usuario = this.usuarioRepository.findByCpf(auth.getName());
		
		if(usuario == null) {
			throw new Exception();
		}
		
		return usuario;
	}


	
}