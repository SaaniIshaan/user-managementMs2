package com.tekarch.user_managementMs2.controller;

import com.tekarch.user_managementMs2.models.AccountDTO;
import com.tekarch.user_managementMs2.models.User;
import com.tekarch.user_managementMs2.services.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserServiceImpl userServiceImpl;

    // Create a new user
    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
            User createdUser = userServiceImpl.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

    }

    //Get All users
    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userServiceImpl.getAllUsers());
    }

    // Get a user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return userServiceImpl.getUserById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get user by username
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userServiceImpl.getUserByUsername(username);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());   // Use the exception here
    }

    // Get user by email
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userServiceImpl.getUserByEmail(email);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());  // Use the exception here
    }

    @PutMapping()
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userServiceImpl.updateUser(user);
        return ResponseEntity.ok(userServiceImpl.updateUser(user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userServiceImpl.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/accounts")
    public ResponseEntity<List<AccountDTO>> getLinkedAccounts(@PathVariable Long userId) {
        List<AccountDTO> accounts = userServiceImpl.getLinkedAccounts(userId);
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{userId}/accounts/{accountId}")
    public ResponseEntity<AccountDTO> getAccountDetails(@PathVariable Long userId, @PathVariable Long accountId) {
        AccountDTO account = userServiceImpl.getAccountDetails(userId, accountId);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/{userId}/accounts")
    public ResponseEntity<Void> addAccount(@PathVariable Long userId, @RequestBody AccountDTO accountDTO) {
        userServiceImpl.addAccount(userId, accountDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{userId}/accounts/{accountId}")
    public ResponseEntity<Void> updateAccount(@PathVariable Long userId, @PathVariable Long accountId, @RequestBody AccountDTO accountDTO) {
        userServiceImpl.updateAccount(userId, accountId, accountDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/accounts/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long userId, @PathVariable Long accountId) {
        userServiceImpl.deleteAccount(userId, accountId);
        return ResponseEntity.ok().build();
    }
}
