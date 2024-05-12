package com.machaware.store.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.machaware.store.models.User;
import com.machaware.store.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUSers() {
		return (List<User>) userRepository.findAll();
	}

	public User getUserEmail(String email) {
		return userRepository.findbyEmail(email).orElseThrow();
	}

	public User getUserId(Long id) {
		return userRepository.findById(id).orElseThrow();
	}

	public ResponseEntity<?> createUser(User user) {
		if (user.getPassword() == "") {
			return ResponseEntity.badRequest().body("Password cannot be empty");
		}

		userRepository.save(user);
		return ResponseEntity.ok().body(user);
	}
}
