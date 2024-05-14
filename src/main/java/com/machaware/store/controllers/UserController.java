package com.machaware.store.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.machaware.store.models.User;
import com.machaware.store.models.DTO.UserDTO;
import com.machaware.store.services.UserService;
import com.machaware.store.utils.Validator;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	private Validator validator = new Validator();

	@GetMapping()
	public List<UserDTO> getAllUSers() {
		return userService.getAllUSers();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id) {
		return userService.getUserId(id);
	}

	@PostMapping()
	public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult result) {
		if (result.hasErrors()) {
			return validator.validation(result);
		}
		return userService.createUser(user);
	}

}
