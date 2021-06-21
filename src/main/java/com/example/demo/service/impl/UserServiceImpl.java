package com.example.demo.service.impl;

import com.example.demo.entities.UserEntity;
import com.example.demo.exceptions.NotFoundIdException;
import com.example.demo.exceptions.UserEntityNotNullException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity addUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> getUserById(Long id){
        Optional<UserEntity> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new NotFoundIdException("Não encontrado registro de usuario correspondente.");
        }
        return user;
    }

    @Override
    public boolean deleteUserById(Long userId) {
        getUserById(userId);
        userRepository.deleteById(userId);
        return true;
    }

    @Override
    public UserEntity updateUser(Long userId, UserEntity userEntity) {
        getUserById(userId);
        validUser(userEntity);
        return userRepository.save(userEntity);
    }

    private Boolean validUser(UserEntity userEntity) {
        if (Objects.isNull(userEntity)){
            throw new UserEntityNotNullException("User não pode estar vazio");
        }
        return true;
    }

}
