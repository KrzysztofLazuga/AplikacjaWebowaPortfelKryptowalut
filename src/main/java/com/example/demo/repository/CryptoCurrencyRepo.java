package com.example.demo.repository;
import com.example.demo.model.CryptoCurrency;
import com.example.demo.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CryptoCurrencyRepo extends JpaRepository<CryptoCurrency, Integer> {
    CryptoCurrency findByname(String search);
}
