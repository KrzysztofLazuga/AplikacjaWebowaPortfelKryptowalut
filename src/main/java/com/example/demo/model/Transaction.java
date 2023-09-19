package com.example.demo.model;
import javax.persistence.*;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type;

    @ManyToOne
    private CryptoCurrency cryptoCurrency;

    private Float quantity;
    private Float cost;

    @Column(name = "date")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;

    public Transaction(String type, CryptoCurrency cryptoCurrency, Float quantity, Float cost, LocalDate date) {
        this.type = type;
        this.cryptoCurrency = cryptoCurrency;
        this.quantity = quantity;
        this.cost = cost;
        this.date = date;
    }

    public Transaction(Integer id, String type, CryptoCurrency cryptoCurrency, Float quantity, Float cost, LocalDate date) {
        this.id = id;
        this.type = type;
        this.cryptoCurrency = cryptoCurrency;
        this.quantity = quantity;
        this.cost = cost;
        this.date = date;
    }

    public Transaction() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CryptoCurrency getCryptoCurrency() {
        return cryptoCurrency;
    }

    public void setCryptoCurrency(CryptoCurrency cryptoCurrency) {
        this.cryptoCurrency = cryptoCurrency;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float price) {
        this.cost = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
