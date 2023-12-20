package com.cg.sakila.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.sakila.enitites.User;

public interface IUserRepo extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);

}
