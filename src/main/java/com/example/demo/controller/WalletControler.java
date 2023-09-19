package com.example.demo.controller;
import com.example.demo.model.CryptoCurrency;
import com.example.demo.model.Transaction;
import com.example.demo.model.Wallet;
import com.example.demo.repository.CryptoCurrencyRepo;
import com.example.demo.repository.TransactionRepo;
import com.example.demo.repository.WalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WalletControler {
    @Autowired
    private WalletRepo walletRepo;
    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private CryptoCurrencyRepo cryptoCurrencyRepo;

    @RequestMapping("/showWallet")
    public String showWallet(Model model) {

        List<CryptoCurrency> cryptoCurrencyList = cryptoCurrencyRepo.findAll();

        for (CryptoCurrency cryptoCurrency : cryptoCurrencyList) {
            Integer cryptoCurrencyId = cryptoCurrency.getId();
            Float totalCost = 0.0f, totalQuantity = 0.0f;

            if (transactionRepo.existsByCryptoCurrencyId(cryptoCurrencyId)) {
                List<Transaction> transactionList = transactionRepo.findAllBycryptoCurrencyId(cryptoCurrencyId);

                for(Transaction transaction : transactionList) {
                    String type = transaction.getType();
                    Float cost = transaction.getCost();
                    Float quantity = transaction.getQuantity();

                    if(type.equals("kupno")) {
                        totalCost += cost;
                        totalQuantity += quantity;
                    } else if (type.equals("sprzedaż")) {
                        totalCost -= cost;
                        totalQuantity -= quantity;
                    }
                }

                Wallet wallet;
                if (walletRepo.existsByCryptoCurrencyId(cryptoCurrencyId)) {
                    wallet = new Wallet(walletRepo.findIdByCryptoCurrencyId(cryptoCurrencyId), cryptoCurrency, totalQuantity, totalCost);
                } else {
                    wallet = new Wallet(cryptoCurrency, totalQuantity, totalCost);
                }
                walletRepo.save(wallet);
            } else {
                if (walletRepo.existsByCryptoCurrencyId(cryptoCurrencyId)) {
                    walletRepo.deleteById(walletRepo.findIdByCryptoCurrencyId(cryptoCurrencyId));
                }
            }
        }

        model.addAttribute( "wallets", walletRepo.findAll());
        return "showWallet";
    }
}
