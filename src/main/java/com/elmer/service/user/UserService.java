package com.elmer.service.user;

import com.elmer.model.user.UserModel;
import com.elmer.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }

    public UserModel save(UserModel user){ return userRepository.save(user); }
    public List<UserModel> allUsers(){ return userRepository.findAll(); }
    public Optional<UserModel> getUserById(String id) { return userRepository.findById(id); }
    public void deleteUser(String id) { userRepository.deleteById(id); }

    public UserModel updateUser(UserModel user, String id){
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setName(user.getName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            return userRepository.save(existingUser);
        }).orElse(null);
    }
}
