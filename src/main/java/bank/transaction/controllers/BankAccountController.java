package bank.transaction.controllers;


import bank.transaction.dao.BankAccountDAO;
import bank.transaction.models.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping({"/bankAccounts"})
public class BankAccountController {
    private final BankAccountDAO bankAccountDAO;

    @Autowired
    public BankAccountController(BankAccountDAO bankAccountDAO) {
        this.bankAccountDAO = bankAccountDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("bankAccounts", this.bankAccountDAO.index());
        return "bankAccounts/index";
    }

    @GetMapping({"/{id}"})
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("bankAccount", this.bankAccountDAO.show(id));
        return "bankAccounts/show";
    }

    @GetMapping({"/new"})
    public String newBankAccount(Model model) {
        model.addAttribute("bankAccount", new BankAccount());
        return "bankAccounts/new";
    }

    @PostMapping
    public String create(@ModelAttribute("bankAccount") @Valid BankAccount bankAccount, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "bankAccounts/new";
        this.bankAccountDAO.save(bankAccount);
        return "redirect:/bankAccounts";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int id){
        model.addAttribute("bankAccount",bankAccountDAO.show(id));
        return "bankAccounts/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("bankAccount") @Valid BankAccount bankAccount,
                         BindingResult bindingResult,@PathVariable("id") int id){
        if (bindingResult.hasErrors())
            return "bankAccounts/edit";
        bankAccountDAO.update(id,bankAccount);
        return "redirect:/bankAccounts";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bankAccountDAO.delete(id);
        return "redirect:/bankAccounts";
    }

}
