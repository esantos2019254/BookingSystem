package com.elmer.controller;

import com.elmer.model.user.UserModel;
import com.elmer.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllUsers() {
        List<UserModel> users = new ArrayList<>();
        users.add(new UserModel());
        when(userService.allUsers()).thenReturn(users);

        ResponseEntity<List<UserModel>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
        verify(userService, times(1)).allUsers();
    }

    @Test
    void testFindUserById() {
        String id = "456";
        UserModel user = new UserModel();
        when(userService.getUserById(id)).thenReturn(Optional.of(user));

        ResponseEntity<UserModel> response = userController.findById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(userService, times(1)).getUserById(id);
    }

    @Test
    void testDeleteUser() {
        String id = "456";
        UserModel user = new UserModel();
        when(userService.getUserById(id)).thenReturn(Optional.of(user));

        ResponseEntity<Void> response = userController.deleteUser(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService, times(1)).deleteUser(id);
    }
}
