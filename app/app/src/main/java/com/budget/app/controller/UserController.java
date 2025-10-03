package com.budget.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.budget.app.entity.User;
import com.budget.app.service.UserService;
import com.budget.app.dto.ApiResponse;



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
       public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user) {
        try {
            User savedUser = userService.createUser(user);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(true, "User created successfully!", savedUser));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, "Failed to create user: " + e.getMessage(), null));
        }
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
public <T> ResponseEntity<ApiResponse<User>> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
    try {
       User updateUser = userService.updateUser(id, userDetails);
       return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true,"User Updated successfully",updateUser));
    } catch (Exception e) {
       
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(false,"Failed to update user" + e.getMessage(),null));
    }
}

// Delete User (DELETE)
@DeleteMapping("/delete/{id}")
public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
    boolean deleted = userService.deleteUser(id);

    if (deleted) {
        return ResponseEntity.ok(new ApiResponse<>(true, "User deleted successfully", null));
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(new ApiResponse<>(false, "User not found with id " + id, null));
    }
}


}
