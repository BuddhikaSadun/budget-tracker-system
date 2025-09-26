package com.budget.app.service;

import com.budget.app.entity.Transaction;
import com.budget.app.respository.*;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    // ✅ Create
    public Transaction createTransaction(Transaction transaction) {
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
