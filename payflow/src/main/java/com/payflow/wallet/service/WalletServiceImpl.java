package com.payflow.wallet.service;

import com.payflow.user.entity.User;
import com.payflow.user.repository.UserRepository;
import com.payflow.wallet.dto.WalletResponse;
import com.payflow.wallet.entity.Wallet;
import com.payflow.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public WalletResponse createWallet(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found"));

        if (walletRepository.existsByUser(user)) {
            throw new IllegalStateException(
                    "Wallet already exists for this user");
        }

        Wallet wallet = new Wallet();
        wallet.setUser(user);

        Wallet savedWallet = walletRepository.save(wallet);

        return toResponse(savedWallet);
    }

    @Override
    @Transactional(readOnly = true)
    public WalletResponse getMyWallet(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found"));

        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(() ->
                        new IllegalArgumentException("Wallet not found"));

        return toResponse(wallet);
    }

    private WalletResponse toResponse(Wallet wallet) {
        return new WalletResponse(
                wallet.getId(),
                wallet.getBalance(),
                wallet.getCurrency(),
                wallet.getCreatedAt()
        );
    }
}