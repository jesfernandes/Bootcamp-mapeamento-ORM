package com.example.demo.service;

import com.example.demo.entities.UserEntity;

import java.util.Optional;

public interface UserService {

    UserEntity addUser(UserEntity userEntity);

    Optional<UserEntity> getUserById(Long userId);

    UserEntity updateUser(Long userId, UserEntity userEntity);

    boolean deleteUserById(Long userId);
}
