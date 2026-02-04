package com.example.msbookspayments.payments.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePaymentRequest
    (@NotNull Long bookId,
    @NotNull @Min(1) Integer units,
    @NotBlank @Email String buyerEmail
    ) {}
