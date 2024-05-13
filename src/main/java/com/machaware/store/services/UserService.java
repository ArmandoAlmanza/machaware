package com.machaware.store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.machaware.store.models.User;
import com.machaware.store.models.DTO.UserDTO;
import com.machaware.store.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;

	public List<User> getAllUSers() {
		return (List<User>) repository.findAll();
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

	public ResponseEntity<?> createUser(User user) {
		repository.save(user);
		return new ResponseEntity<>("User added succesfully", HttpStatus.CREATED);
	}

	public ResponseEntity<?> update(Long id, User user) {
		Optional<User> userDB = repository.findById(id);
		if (userDB.isPresent()) {
			User userUpdated = userDB.orElseThrow();

			userUpdated.setId(user.getId());
			userUpdated.setFirstName(user.getFirstName());
			userUpdated.setLastName(user.getLastName());
			userUpdated.setId(user.getId());
			userUpdated.setPassword(user.getPassword());

			repository.save(userUpdated);
			return new ResponseEntity<>("User Updated " + userUpdated, HttpStatus.CREATED);

		}
		return new ResponseEntity<>("User not founded", HttpStatus.NOT_FOUND);
	}
}
