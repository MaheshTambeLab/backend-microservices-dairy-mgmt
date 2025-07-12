package com.maheshtambelabs.dairymgmt.userservice.service;

import com.maheshtambelabs.dairymgmt.userservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Optional<User> getUserById(String id);
    User updateUser(String id, User userDetails);
    void deleteUser(String id);
    List<User> getAllUsers();
    boolean existsByEmail(String email);
}
