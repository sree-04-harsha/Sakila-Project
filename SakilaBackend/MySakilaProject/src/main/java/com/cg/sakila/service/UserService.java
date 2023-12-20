package com.cg.sakila.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cg.sakila.dto.UpdateRequest;
import com.cg.sakila.enitites.User;
import com.cg.sakila.repositories.IUserRepo;

@Service
public class UserService {

	@Autowired
	private IUserRepo userRepository;

	@Autowired
	private RestTemplate restTemplate;

	private Logger logger = LoggerFactory.getLogger(UserService.class);

	public User getUserDetails(String email) {

		Optional<User> user = userRepository.findByEmail(email);

		return user.get();
	}

	public boolean updateUser(int id, UpdateRequest user) {
		User u1 = userRepository.findById(id).get();

		if (u1 != null) {

			u1.setEmail(user.getEmail());
			u1.setFirstname(user.getFirstname());
			u1.setLastname(user.getLastname());

			userRepository.save(u1);
			return true;
		}

		return false;

	}


}
