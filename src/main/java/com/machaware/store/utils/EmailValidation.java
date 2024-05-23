package com.machaware.store.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.machaware.store.services.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class EmailValidation implements ConstraintValidator<UserValidation, String> {

	@Autowired
	private UserService service;

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return !service.findByEmail(email);
	}

}
