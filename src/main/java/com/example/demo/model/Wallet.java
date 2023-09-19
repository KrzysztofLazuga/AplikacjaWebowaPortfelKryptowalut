package com.example.demo.model;
import javax.persistence.*;
@Entity
@Table(name = "Wallets")

public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private CryptoCurrency cryptoCurrency;

    private Float totalQuantity;
    private Float totalCost;
    private Float avgPrice;
    private Float profitLoss;
    public Wallet(CryptoCurrency cryptoCurrency, Float totalQuantity, Float totalCost) {
        this.cryptoCurrency = cryptoCurrency;
        this.totalQuantity = totalQuantity;
        this.totalCost = totalCost;
        this.avgPrice = totalCost / totalQuantity;
        Float currentPrice = cryptoCurrency.getPrice();
        this.profitLoss = (currentPrice - avgPrice) * totalQuantity;
    }

    public Wallet(Integer id, CryptoCurrency cryptoCurrency, Float totalQuantity, Float totalCost) {
        this.id = id;
        this.cryptoCurrency = cryptoCurrency;
        this.totalQuantity = totalQuantity;
        this.totalCost = totalCost;
        this.avgPrice = totalCost / totalQuantity;
        Float currentPrice = cryptoCurrency.getPrice();
        this.profitLoss = (currentPrice - avgPrice) * totalQuantity;
    }
    public Wallet() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CryptoCurrency getCryptoCurrency() {
        return cryptoCurrency;
    }

    public void setCryptoCurrency(CryptoCurrency cryptoCurrency) {
        this.cryptoCurrency = cryptoCurrency;
    }

    public Float getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Float totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Float totalCost) {
        this.totalCost = totalCost;
    }

    public Float getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Float avgPrice) {
        this.avgPrice = avgPrice;
    }

    public Float getProfitLoss() {
        return profitLoss;
    }

    public void setProfitLoss(Float profitLoss) {
        this.profitLoss = profitLoss;
    }
}
