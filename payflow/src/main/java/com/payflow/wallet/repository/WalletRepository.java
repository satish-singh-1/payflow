package com.payflow.wallet.repository;

import com.payflow.user.entity.User;
import com.payflow.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByUser(User user);

    boolean existsByUser(User user);
}