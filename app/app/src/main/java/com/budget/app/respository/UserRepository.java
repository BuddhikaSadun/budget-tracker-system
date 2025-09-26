package com.budget.app.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.budget.app.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // extra query methods if needed (e.g., findByEmail)
    User findByEmail(String email);
}
