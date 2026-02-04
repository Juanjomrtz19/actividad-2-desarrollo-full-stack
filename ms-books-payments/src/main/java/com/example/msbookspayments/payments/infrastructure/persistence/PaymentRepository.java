package com.example.msbookspayments.payments.infrastructure.persistence;

import com.example.msbookspayments.payments.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}
