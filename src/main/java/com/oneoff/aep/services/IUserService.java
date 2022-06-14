package com.oneoff.aep.services;

import com.oneoff.aep.data.UserDTO;
import com.oneoff.aep.entities.User;
import com.oneoff.aep.exception.UserException;

public interface IUserService {

	
	public UserDTO deletar(final Long cpf );
	
	public User buscaPorId(final Long cpf);

	public User atualizarUsuario(User user) throws UserException;
	
	public User saveUser(UserDTO user) throws UserException;

	public User findAuth() throws Exception;

}
