package com.budget.app.service;

import com.budget.app.entity.Budget;
import com.budget.app.entity.Transaction;
import com.budget.app.entity.TransactionType;
import com.budget.app.entity.User;
import com.budget.app.respository.*;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
private final UserRepository userRepository; 
    private final BudgetRepository budgetRepository;

    public TransactionService(TransactionRepository transactionRepository, BudgetRepository budgetRepository,UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
    }

    // ✅ Create transaction with budget check
  public Transaction createTransaction(Transaction transaction) {
    // Fetch full user details from DB using userId
    Long userId = transaction.getUser().getUserId();
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
    transaction.setUser(user); // set the full user object

    // Budget check for EXPENSE
    if (transaction.getType() == TransactionType.EXPENSE) {
        // Get total expenses for this user
        int totalExpenses = transactionRepository.findAll().stream()
                .filter(t -> t.getUser().getUserId().equals(user.getUserId()) &&
                             t.getType() == TransactionType.EXPENSE)
                .mapToInt(Transaction::getAmount)
                .sum();

        int newTotal = totalExpenses + transaction.getAmount();

        // Find user’s budget
        Budget budget = budgetRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Budget not set for user"));

        if (newTotal > budget.getAmount()) {
            throw new RuntimeException("Transaction exceeds budget! Allowed: "
                    + budget.getAmount() + ", Current: " + totalExpenses);
        }
    }

    return transactionRepository.save(transaction);
}

    
    // ✅ Get all
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // ✅ Get by id
    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    // ✅ Update
    public Transaction updateTransaction(Long id, Transaction updatedTransaction) {
        return transactionRepository.findById(id).map(transaction -> {
            transaction.setType(updatedTransaction.getType());
            transaction.setAmount(updatedTransaction.getAmount());
            transaction.setTransactionDate(updatedTransaction.getTransactionDate());
            transaction.setUser(updatedTransaction.getUser());
            transaction.setCategory(updatedTransaction.getCategory());
            return transactionRepository.save(transaction);
        }).orElseThrow(() -> new RuntimeException("Transaction not found with id " + id));
    }

    // ✅ Delete
    public void deleteTransaction(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new RuntimeException("Transaction not found with id " + id);
        }
        transactionRepository.deleteById(id);
    }
}
