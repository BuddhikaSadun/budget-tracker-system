package com.budget.app.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.budget.app.entity.User;
import com.budget.app.respository.UserRepository;


@Service
public class UserService {
     private final UserRepository userRepository;

    // Constructor
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get single user
    public User getUserById(Long id, String msg) {
        return userRepository.findById(id).orElse(null);
    }

    //update by id
    public User updateUser(Long id, User userDetails) {
    return userRepository.findById(id)
            .map(user -> {
                user.setName(userDetails.getName());
                user.setEmail(userDetails.getEmail());
                // add other fields you want to update
                return userRepository.save(user);
            })
            .orElse(null);
}

// delete by id
public boolean deleteUser(Long id) {
    return userRepository.findById(id)
            .map(user -> {
                userRepository.delete(user);
                return true;
                
            })
            .orElse(false);
}
}
