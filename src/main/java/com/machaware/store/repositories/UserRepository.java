package com.machaware.store.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.machaware.store.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByEmail(String email);
}
