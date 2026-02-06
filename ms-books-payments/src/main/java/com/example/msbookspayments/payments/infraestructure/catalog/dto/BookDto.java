package com.example.msbookspayments.payments.infraestructure.catalog.dto;

public record BookDto(
        Long id,
        Boolean visibility,
        Integer stock
) {}
