package com.payflow.wallet.controller;

import com.payflow.wallet.dto.WalletResponse;
import com.payflow.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping
    public ResponseEntity<WalletResponse> createWallet(
            Authentication authentication) {

        WalletResponse response =
                walletService.createWallet(authentication.getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/me")
    public ResponseEntity<WalletResponse> getMyWallet(
            Authentication authentication) {

        return ResponseEntity.ok(
                walletService.getMyWallet(authentication.getName())
        );
    }
}