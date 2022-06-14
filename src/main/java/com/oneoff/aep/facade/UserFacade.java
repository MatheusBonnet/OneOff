package com.oneoff.aep.facade;

import com.oneoff.aep.data.UserDTO;
import com.oneoff.aep.entities.Role;
import com.oneoff.aep.repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneoff.aep.entities.User;
import com.oneoff.aep.facade.populators.Populator;
import com.oneoff.aep.repositories.IUserRepository;
import com.oneoff.aep.services.IUserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserFacade {

	@Autowired
	private Populator<UserDTO, User> userReversePopulator;
	
	@Autowired
	private IUserService userService;
	
	
	@Autowired
	private IUserRepository repository;

	@Autowired
	private IRoleRepository roleRepository;

	public Populator<UserDTO, User> getUserReversePopulator() {
		return userReversePopulator;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public void setUserReversePopulator(Populator<UserDTO, User> userReversePopulator) {
		this.userReversePopulator = userReversePopulator;
	}
	
	public IUserRepository getRepository() {
		return repository;
	}

	public void setRepository(IUserRepository repository) {
		this.repository = repository;
	}
	
	public User registerUser(UserDTO userDTO) {
		User user = new User();
		getUserReversePopulator().populate(userDTO, user);

		if(userDTO.getEmail().equalsIgnoreCase("admin@gmail.com")){
			List<Role> roles = roleRepository.findByRole("ADMINISTRADOR");
			user.setRoles(roles);
		}else {
			List<Role> roles = roleRepository.findByRole("USUARIO_NORMAL");
			user.setRoles(roles);
		}
		getRepository().save(user);
		return user;
	}
	
	public User updateDataUser(User user) {
		getRepository().save(user);
		return user;
	}

	
}
