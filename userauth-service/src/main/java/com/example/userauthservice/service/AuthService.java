package com.example.userauthservice.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.userauthservice.model.User;
import com.example.userauthservice.repository.UserRepository;

@Service
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User registerUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public String loginUser(String username, String password) {
		// Validate user credentials against stored data
		User user = userRepository.findByUsername(username).orElse(null);
		if (user != null && passwordEncoder.matches(password, user.getPassword())) {
			// Issue a token (this is a simplified example)
			return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...";
		}
		return null;
	}

	public User authenticateUser(String username, String password) {
		User user = userRepository.findByUsername(username).orElse(null);
		if (user != null && passwordEncoder.matches(password, user.getPassword())) {
			return user;
		}
		return null;
	}
}
