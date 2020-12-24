package bank.transaction.controllers;


import bank.transaction.dao.TransactionDAO;
import bank.transaction.models.BankAccount;
import bank.transaction.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping({"/transactions"})
public class TransactionController {
    private final TransactionDAO transactionDAO;

    @Autowired
    public TransactionController(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("transaction", this.transactionDAO.index());
        return "transactions/index";
    }

    @GetMapping({"/new"})
    public String newTransaction(Model model) {
        model.addAttribute("transaction", new BankAccount());
        return "transactions/new";
    }

    @PostMapping
    public String create(@ModelAttribute("transaction") @Valid Transaction transaction, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "transactions/new";
        this.transactionDAO.save(transaction);
        return "redirect:/transactions";
    }
}
