package com.oneoff.aep.facade.populators;

import org.springframework.stereotype.Service;

import com.oneoff.aep.DTO.UserDTO;
import com.oneoff.aep.entities.User;

@Service
public class UserPopulator implements Populator<User, UserDTO>{

	@Override
	public void populate(User source, UserDTO target) {	
		target.setId(source.getId());
		target.setNome(source.getNome());
		target.setEmail(source.getEmail());
		target.setCpf(source.getCpf());
		target.setPassword(source.getPassword());
	}	
}
