package com.generations.loja_games_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generations.loja_games_api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

  public Optional<User> findByUsername(String username);

}
