package com.elmer.service.user;

import com.elmer.repository.user.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserModel createUser(UserModel user);

    List<UserModel> getAllUsers();

    Optional<UserModel> getUserById(Long id);

    void deleteUser (Long id);

    UserModel updateUser (UserModel user, Long i);
}
