package com.nexted.UserService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexted.UserService.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
