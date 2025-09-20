package com.budget.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.budget.app.entity.User;
import com.budget.app.service.UserService;



@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserService userService;

     public UserController(UserService userService) {
        this.userService = userService;
    }

     // Create User (POST)
    @PostMapping("/create")
    @ResponseStatus(code= HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Get all Users (GET)
    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get single User by ID (GET)
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id, null);
    }

    // Update User (PUT)
@PutMapping("/update/{id}")
public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
    return userService.updateUser(id, userDetails);
}

// Delete User (DELETE)
@DeleteMapping("/delete/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void deleteUser(@PathVariable Long id) {
    boolean deleted = userService.deleteUser(id);
    if (!deleted) {
        throw new RuntimeException("User not found with id " + id);
    }
}

}
