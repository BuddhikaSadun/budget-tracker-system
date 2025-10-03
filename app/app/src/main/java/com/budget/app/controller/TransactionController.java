package com.budget.app.controller;

import com.budget.app.dto.ApiResponse;
import com.budget.app.entity.Transaction;
import com.budget.app.service.TransactionService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // ✅ Create transaction
     @PostMapping("/create")
    public ResponseEntity<ApiResponse<Transaction>> createTransaction(@RequestBody Transaction transaction) {
        try {
            Transaction saveTransaction = transactionService.createTransaction(transaction);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Transaction created ", saveTransaction));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    // ✅ Get all transactions
    @GetMapping("/")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    // ✅ Get transaction by ID
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionService.getTransactionById(id);
        return transaction.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Update transaction
    @PutMapping("/update/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        try {
            Transaction updated = transactionService.updateTransaction(id, transaction);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Delete transaction
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long id) {
        try {
            transactionService.deleteTransaction(id);
            return ResponseEntity.ok("Transaction with ID " + id + " deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
