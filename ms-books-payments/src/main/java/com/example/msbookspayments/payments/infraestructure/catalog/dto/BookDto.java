package com.example.msbookspayments.payments.infraestructure.catalog.dto;

public record BookDto(
        Long id,
        Boolean visible,
        Integer stock
) {}
