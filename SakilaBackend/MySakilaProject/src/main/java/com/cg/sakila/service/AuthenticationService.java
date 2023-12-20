package com.cg.sakila.service;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.sakila.dto.AuthenticationRequest;
import com.cg.sakila.dto.AuthenticationResponse;
import com.cg.sakila.dto.RegisterRequest;
import com.cg.sakila.enitites.Role;
import com.cg.sakila.enitites.User;
import com.cg.sakila.exception.CustomException;
import com.cg.sakila.repositories.IUserRepo;
import com.cg.sakila.security.JwtService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	@Autowired
	private final IUserRepo userRepository;

	@Autowired
	private final PasswordEncoder passwordEncoder;

	@Autowired
	private final JwtService jwtService;

	@Autowired
	private final AuthenticationManager authenticationManager;

	public AuthenticationResponse register(RegisterRequest request) {

		var user = User.builder().firstname(request.getFirstname()).lastname(request.getLastname())
				.email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
				.role(Role.valueOf(request.getRole().toUpperCase())).build();

		Optional<User> getUser = userRepository.findByEmail(request.getEmail());

		if (getUser.isEmpty()) {
			userRepository.save(user);
			var jwtToken = jwtService.generateToken((UserDetails) user);
			return AuthenticationResponse.builder().jwtToken(jwtToken).username(user.getFirstname())
					.role(user.getRole().toString()).build();
		} else {
			throw new CustomException("User has already registered");
		}

	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {

		var user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new CustomException("User not found with email :" + request.getEmail()));

		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

		user.setLogged(true);
		userRepository.save(user);

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {

				user.setLogged(false);
				userRepository.save(user);

			}
		}, 1000 * 60 * 120);

		var jwtToken = jwtService.generateToken(user);

		return AuthenticationResponse.builder().jwtToken(jwtToken).username(user.getFirstname())
				.role(user.getRole().toString()).build();
//		}
//		return AuthenticationResponse.builder().jwtToken("You are alreday login").build();
	}

	public User changePassword(AuthenticationRequest request) {

		Optional<User> user2 = userRepository.findByEmail(request.getEmail());
		User user = user2.get();

		if (user2.isPresent()) {

			user.setPassword(passwordEncoder.encode(request.getPassword()));

		} else {
			throw new CustomException("User not found with email :" + request.getEmail());
		}
		return userRepository.save(user);
	}

	public User getUserById(int id) {

		return userRepository.findById(id).orElseThrow(() -> new CustomException("User not found !!"));
	}

	public boolean deletebyid(Integer id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			userRepository.getById(id);
			return true;
		}
		return false;
	}

	public boolean signout(int id) {

		Optional<User> user = userRepository.findById(id);
		User u1 = user.get();
		if (u1 != null && (u1.isLogged())) {
			u1.setLogged(false);
			userRepository.save(u1);
			return true;

		}
		return false;

	}

}
