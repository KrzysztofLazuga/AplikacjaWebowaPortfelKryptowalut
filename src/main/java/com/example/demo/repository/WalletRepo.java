package com.example.demo.repository;
        import com.example.demo.model.Wallet;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, Integer> {
        @Query("SELECT COUNT(w) > 0 FROM Wallet w WHERE w.cryptoCurrency.id = :cryptoCurrencyId")
        boolean existsByCryptoCurrencyId(Integer cryptoCurrencyId);

        @Query("SELECT w.id FROM Wallet w WHERE w.cryptoCurrency.id = :cryptoCurrencyId")
        Integer findIdByCryptoCurrencyId(Integer cryptoCurrencyId);

}
