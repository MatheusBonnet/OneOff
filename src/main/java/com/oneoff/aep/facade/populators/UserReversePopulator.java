package com.oneoff.aep.facade.populators;

import org.springframework.stereotype.Service;

import com.oneoff.aep.data.UserDTO;
import com.oneoff.aep.entities.User;

@Service
public class UserReversePopulator implements Populator<UserDTO, User>{

	@Override
	public void populate(UserDTO source, User target) {
		target.setId(source.getId());
		target.setNome(source.getNome());
		target.setEmail(source.getEmail());
		target.setCpf(source.getCpf());
		target.setPassword(source.getPassword());
	}

}
