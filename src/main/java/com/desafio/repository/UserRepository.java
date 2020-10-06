package com.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.desafio.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByLogin(String login);

}

