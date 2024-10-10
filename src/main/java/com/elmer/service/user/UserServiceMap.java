package com.elmer.service.user;

import com.elmer.repository.user.UserModel;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceMap implements UserService {
    private final Map<Long, UserModel> users = new HashMap<>();

    @Override
    public UserModel createUser(UserModel user) {
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public List<UserModel> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public Optional<UserModel> getUserById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public void deleteUser(Long id) {
        users.remove(id);
    }

    @Override
    public UserModel updateUser(UserModel user, Long id) {
        users.put(id, user);
        return user;
    }
}
