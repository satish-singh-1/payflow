package com.payflow.wallet.service;

import com.payflow.wallet.dto.WalletResponse;

public interface WalletService {

    WalletResponse createWallet(String email);

    WalletResponse getMyWallet(String email);
}