package com.budget.app.controller;

import com.budget.app.entity.Budget;
import com.budget.app.entity.User;
import com.budget.app.service.BudgetService;
import com.budget.app.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetService budgetService;
    private final UserService userService;

    public BudgetController(BudgetService budgetService, UserService userService) {
        this.budgetService = budgetService;
        this.userService = userService;
    }

    @PostMapping("/set/{userId}")
    public Budget setBudget(@PathVariable Long userId, @RequestParam int amount) {
        User user = userService.getUserById(userId, null);
        return budgetService.setBudget(user, amount);
    }

    @GetMapping("/{userId}")
    public Budget getBudget(@PathVariable Long userId) {
        User user = userService.getUserById(userId, null);
        return budgetService.getBudgetByUser(user).orElse(null);
    }
}
