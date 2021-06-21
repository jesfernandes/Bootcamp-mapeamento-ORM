package com.example.demo.controllers;

import com.example.demo.entities.UserEntity;
import com.example.demo.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

   private final UserServiceImpl userService;

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<UserEntity>> getUser(@RequestHeader("userId") @Validated Long userId) {
        Optional<UserEntity> getUser = userService.getUserById(userId);
        return new ResponseEntity<Optional<UserEntity>>(getUser, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserEntity> addUser(@RequestBody @Validated UserEntity userEntity) {
        UserEntity newUser = userService.addUser(userEntity);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UserEntity> updateUser(@RequestHeader("userId") @Validated Long userId, @RequestBody @Validated UserEntity userEntity){
        Optional<UserEntity> userIn = Optional.ofNullable(userService.updateUser(userId, userEntity));
        if (!userIn.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            userEntity.setId(userIn.get().getId());
            return new ResponseEntity<UserEntity>(userService.updateUser(userId, userEntity), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Optional<UserEntity>> deleteUser(@RequestHeader("userId") @Validated Long userId) {
        Optional<UserEntity> userIn = userService.getUserById(userId);
        if (!userIn.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            userService.deleteUserById(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
