package com.maheshtambelabs.dairymgmt.userservice.controller;

import com.maheshtambelabs.dairymgmt.userservice.dto.UserDTO;
import com.maheshtambelabs.dairymgmt.userservice.model.User;
import com.maheshtambelabs.dairymgmt.userservice.service.UserService;
import com.maheshtambelabs.dairymgmt.userservice.util.DTOConverter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody User user) {
        try {
            User savedUser = userService.saveUser(user);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(DTOConverter.convertToUserDTO(savedUser));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String id) {
        return userService.getUserById(id)
                .map(DTOConverter::convertToUserDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @RequestBody User userDetails) {
        try {
            User updatedUser = userService.updateUser(id, userDetails);
            return ResponseEntity.ok(DTOConverter.convertToUserDTO(updatedUser));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(DTOConverter::convertToUserDTO)
                .collect(Collectors.toList());
    }
}
