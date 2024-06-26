package com.machaware.store.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.machaware.store.models.User;
import com.machaware.store.models.DTO.UserDTO;
import com.machaware.store.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<UserDTO> getAllUSers() {
		List<User> allUsers = (List<User>) repository.findAll();

		return allUsers.stream()
				.map(user -> {
					UserDTO userDTO = new UserDTO();
					userDTO.setName(user.getFirstName() + " " + user.getLastName());
					userDTO.setEmail(user.getEmail());
					userDTO.setRol(String.valueOf(user.getRol()));
					return userDTO;
				})
				.collect(Collectors.toList());
	}

	public ResponseEntity<?> getUserId(Long id) {
		Optional<User> user = repository.findById(id);
		UserDTO userDTO = new UserDTO();
		if (user.isPresent()) {
			userDTO.setName(user.get().getFirstName() + " " + user.get().getLastName());
			userDTO.setEmail(user.get().getEmail());
			userDTO.setRol(String.valueOf(user.get().getRol()));
			return new ResponseEntity<>(userDTO, HttpStatus.OK);
		}
		return new ResponseEntity<>("User not faounded", HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<?> getUserEmail(String email) {
		Optional<User> user = repository.findByEmail(email);
		UserDTO userDTO = new UserDTO();
		if (user.isPresent()) {
			userDTO.setName(user.get().getFirstName() + " " + user.get().getLastName());
			userDTO.setEmail(user.get().getEmail());
			userDTO.setRol(String.valueOf(user.get().getRol()));
			return new ResponseEntity<>(userDTO, HttpStatus.OK);
		}
		return new ResponseEntity<>("User not faounded", HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<?> createUser(User user) {
		Optional<User> userDB = repository.findByEmail(user.getEmail());
		if (userDB.isPresent()) {
			return new ResponseEntity<>("The email is already in use", HttpStatus.NOT_FOUND);
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		repository.save(user);
		return new ResponseEntity<>("User added succesfully", HttpStatus.CREATED);
	}

	public ResponseEntity<?> update(Long id, User user) {
		Optional<User> userDB = repository.findById(id);
		if (userDB.isPresent()) {
			User userUpdated = userDB.get();

			userUpdated.setId(user.getId());
			userUpdated.setFirstName(user.getFirstName());
			userUpdated.setLastName(user.getLastName());
			userUpdated.setId(user.getId());
			userUpdated.setPassword(passwordEncoder.encode(user.getPassword()));

			repository.save(userUpdated);
			return new ResponseEntity<>("User Updated " + userUpdated, HttpStatus.CREATED);

		}
		return new ResponseEntity<>("User not founded", HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<?> delete(Long id) {
		Optional<User> userDB = repository.findById(id);
		userDB.ifPresent(user -> repository.delete(user));
		return new ResponseEntity<>("User deleted", HttpStatus.OK);
	}

	public boolean findByEmail(String email) {
		return repository.findByEmail(email).isPresent();
	}
}
