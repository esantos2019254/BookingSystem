package com.elmer.controller;

import com.elmer.repository.user.UserModel;
import com.elmer.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    private final UserService userService;
    Long contador = 0L;
    public UserController(UserService userService) { this.userService = userService; }

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user){
        user.setId(contador++);
        userService.createUser(user);
        log.info("Usuario creado exitosamente: {}", user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> listUsers = userService.getAllUsers();
        return ResponseEntity.ok(listUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> findById(@PathVariable("id") Long id){
        Optional<UserModel> user = userService.getUserById(id);
        if(user.isPresent()){
            log.info("Usuario encontrado: {}", user.get());
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
        Optional<UserModel> user = userService.getUserById(id);
        if(user.isPresent()){
            userService.deleteUser(id);
            log.info("Usuario Eliminardo: {}", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUser(@RequestBody UserModel userModel, @PathVariable("id") Long id){
        Optional<UserModel> user = userService.getUserById(id);
        if(user.isPresent()){
            UserModel getUser = user.get();
            getUser.setName(userModel.getName());
            getUser.setEmail(userModel.getEmail());
            getUser.setLastName(userModel.getLastName());
            getUser.setPassword(userModel.getPassword());
            userService.updateUser(getUser, id);
            log.info("Usuario Actualizado: {}", getUser);
            return new ResponseEntity<>(getUser, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
