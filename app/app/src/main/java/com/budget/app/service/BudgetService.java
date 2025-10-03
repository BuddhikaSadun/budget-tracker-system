package com.budget.app.service;

import com.budget.app.entity.Budget;
import com.budget.app.entity.User;
import com.budget.app.respository.BudgetRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;

    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public Budget setBudget(User user, int amount) {
        return budgetRepository.findByUser(user)
                .map(budget -> {
                    budget.setAmount(amount);
                    return budgetRepository.save(budget);
                })
                .orElseGet(() -> {
                    Budget budget = new Budget();
                    budget.setUser(user);
                    budget.setAmount(amount);
                    return budgetRepository.save(budget);
                });
    }

    public Optional<Budget> getBudgetByUser(User user) {
        return budgetRepository.findByUser(user);
    }
}
