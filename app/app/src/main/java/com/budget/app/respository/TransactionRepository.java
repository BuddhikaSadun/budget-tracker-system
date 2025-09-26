package com.budget.app.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.budget.app.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
