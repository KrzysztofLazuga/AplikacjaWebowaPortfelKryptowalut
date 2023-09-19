package com.example.demo.repository;
import com.example.demo.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository <Transaction, Integer> {
    List<Transaction> findAllBycryptoCurrencyName(String search);

    List<Transaction> findAllBycryptoCurrencyId(Integer search);

    @Query("SELECT COUNT(w) > 0 FROM Transaction w WHERE w.cryptoCurrency.id = :cryptoCurrencyId")
    boolean existsByCryptoCurrencyId(Integer cryptoCurrencyId);
}
