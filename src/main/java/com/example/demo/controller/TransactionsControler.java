package com.example.demo.controller;

import com.example.demo.model.CryptoCurrency;
import com.example.demo.model.Transaction;
import com.example.demo.repository.CryptoCurrencyRepo;
import com.example.demo.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class TransactionsControler {
    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private CryptoCurrencyRepo cryptoCurrencyRepo;

    @RequestMapping("/addTransactionForm")
    public String addTransactionForm(Model model) {
        List<CryptoCurrency> currencies = cryptoCurrencyRepo.findAll();
        model.addAttribute("currencies", currencies);
        return "addTransactionForm";
    }
    @RequestMapping("/addTransaction")
    public String addTransaction (
            @RequestParam("type") String type,
            @RequestParam ("cryptoCurrencyName" ) String cryptoCurrencyName,
            @RequestParam ("quantity" ) Float quantity,
            @RequestParam ("cost") Float cost,
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            Model model)
            throws Exception {

        Transaction transaction = new Transaction(type, cryptoCurrencyRepo.findByname(cryptoCurrencyName), quantity, cost, date);

        if (quantity == null || cost == null || date == null) {
            model.addAttribute("transaction", transaction);
            model.addAttribute("transactionDate", date);

            List<CryptoCurrency> currencies = cryptoCurrencyRepo.findAll();
            model.addAttribute("currencies", currencies);

            return "addTransactionFormError";
        } else {
            transactionRepo.save(transaction);
            model.addAttribute( "transactions", transactionRepo.findAll());
            return "showTransactions";
        }
    }

    @RequestMapping("/updateTransaction")
    public String updateTransaction(
            @RequestParam("id") Integer id,
            @RequestParam("type") String type,
            @RequestParam("cryptoCurrencyName") String cryptoCurrencyName,
            @RequestParam("quantity") Float quantity,
            @RequestParam("cost") Float cost,
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            Model model) throws Exception {

        Transaction transaction = new Transaction(id, type, cryptoCurrencyRepo.findByname(cryptoCurrencyName), quantity, cost, date);

        if (quantity == null || cost == null || date == null) {
            model.addAttribute("transaction", transaction);

            model.addAttribute("transactionDate", date);

            List<CryptoCurrency> currencies = cryptoCurrencyRepo.findAll();
            model.addAttribute("currencies", currencies);
            return "updateTransactionError";
        } else {
            transactionRepo.save(transaction);
            model.addAttribute("transactions", transactionRepo.findAll());
            return "showTransactions";
        }
    }


    @RequestMapping ("/showTransactions")
    public String showTransactions(Model model) {
        model.addAttribute( "transactions", transactionRepo.findAll());
        return "showTransactions";
    }

    @RequestMapping ("/deleteTransaction" )
    public String deleteTransaction(@RequestParam ("id") Integer id , Model model) {
        transactionRepo.deleteById(id);
        model.addAttribute( "transactions", transactionRepo .findAll()) ;
        return "showTransactions";
    }

    @RequestMapping ("/search" )
    public String search(@RequestParam ("waluta" ) String waluta, Model model) {
        model.addAttribute( "transactions", transactionRepo.findAllBycryptoCurrencyName(waluta)) ;
        return "showTransactions";
    }

    @RequestMapping("/redirectTransaction")
    public String redirectTransaction(
            @RequestParam("id") Integer id, Model model)
            throws Exception {
        Optional<Transaction> tra = transactionRepo.findById(id);

        Transaction transaction = tra.get();
        LocalDate date = transaction.getDate();
        model.addAttribute("transactionDate", date);

        model.addAttribute("transaction", transaction);
        List<CryptoCurrency> currencies = cryptoCurrencyRepo.findAll();
        model.addAttribute("currencies", currencies);

        return "updateTransaction";
    }
}
