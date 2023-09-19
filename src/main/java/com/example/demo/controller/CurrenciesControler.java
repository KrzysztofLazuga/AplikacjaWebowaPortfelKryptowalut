package com.example.demo.controller;

import com.example.demo.model.CryptoCurrency;
import com.example.demo.repository.CryptoCurrencyRepo;
import com.example.demo.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CurrenciesControler {
    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private CryptoCurrencyRepo cryptoCurrencyRepo;

    @RequestMapping("/addCurrencyForm")
    public String addCurrencyForm() {
        return "addCurrencyForm";
    }

    @RequestMapping("/addCurrency")
    public String addCurrency(
            @RequestParam("name") String name,
            @RequestParam("price") Float price,
            Model model)
            throws Exception {
        CryptoCurrency currency = new CryptoCurrency(name, price);
        cryptoCurrencyRepo.save(currency);
        model.addAttribute( "currencies", cryptoCurrencyRepo.findAll());
        return "showCurrencies";
    }

    @RequestMapping ("/updateCurrency" )
    public String updateCurrency(
            @RequestParam("id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("price") Float price,
            Model model)
            throws Exception {
        CryptoCurrency currencies = new CryptoCurrency(id, name, price);
        cryptoCurrencyRepo.save(currencies);
        model.addAttribute( "currencies", cryptoCurrencyRepo.findAll());
        return "showCurrencies";
    }

    @RequestMapping ("/showCurrencies")
    public String showCurrencies(Model model) {
        model.addAttribute( "currencies", cryptoCurrencyRepo.findAll());
        return "showCurrencies";
    }

    @RequestMapping ("/deleteCurrency" )
    public String deleteCurrency(
            @RequestParam ("id") Integer id,
            @RequestParam("name") String name,
            Model model) {
        if (transactionRepo.findAllBycryptoCurrencyName(name).isEmpty()) {
            cryptoCurrencyRepo.deleteById(id);
        } else {
            return "warning";
        }
        model.addAttribute( "currencies", cryptoCurrencyRepo.findAll());
        return "showCurrencies";
    }

    @RequestMapping("/redirectCurrency")
    public String redirectCurrency(
            @RequestParam("id") Integer id, Model model)
            throws Exception {
        model.addAttribute("currency", cryptoCurrencyRepo.findById(id));
        return "updateCurrency";
    }
}

