package com.machaware.store.repositories;

import org.springframework.data.repository.CrudRepository;

import com.machaware.store.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
