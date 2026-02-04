package com.example.msbookspayments.payments.api.dto;

import com.example.msbookspayments.payments.domain.Payment;
import com.example.msbookspayments.payments.domain.PaymentStatus;

import java.time.Instant;

public record PaymentResponse(
        String id,
        Long bookId,
        Integer units,
        String buyerEmail,
        PaymentStatus status,
        Instant createdAt
) {
    public static PaymentResponse from (Payment p){
        return new PaymentResponse(
                p.getId(),
                p.getBookId(),
                p.getUnits(),
                p.getBuyerEmail(),
                p.getStatus(),
                p.getCreatedAt()
        );
    }
}
