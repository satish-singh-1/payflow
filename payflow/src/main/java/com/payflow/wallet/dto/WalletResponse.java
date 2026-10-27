package com.payflow.wallet.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record WalletResponse(
        Long id,
        BigDecimal balance,
        String currency,
        LocalDateTime createdAt
) {}