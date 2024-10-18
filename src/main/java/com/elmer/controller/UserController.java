package com.elmer.controller;

import com.elmer.model.user.UserModel;
import com.elmer.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) { this.userService = userService; }

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user){
        userService.save(user);
        log.info("Usuario creado exitosamente: {}", user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> listUsers = userService.allUsers();
        return ResponseEntity.ok(listUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> findById(@PathVariable("id") String id){
        return userService.getUserById(id).map(user ->{
            log.info("Usuario encontrado: {}", user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id){
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
    public ResponseEntity<UserModel> updateUser(@RequestBody UserModel userModel, @PathVariable("id") String id){
        UserModel user = userService.updateUser(userModel, id);
        if(user!=null){
            log.info("Usuario actualizado {}", user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
