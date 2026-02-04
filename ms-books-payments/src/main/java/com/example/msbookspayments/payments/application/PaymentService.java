package com.example.msbookspayments.payments.application;

import com.example.msbookspayments.payments.domain.Payment;
import com.example.msbookspayments.payments.infrastructure.persistence.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public Payment createPayment(Long bookId, Integer units, String buyerEmail){
        Payment payment = Payment.confirmed(bookId, units, buyerEmail);
        return paymentRepository.save(payment);
    }
}
