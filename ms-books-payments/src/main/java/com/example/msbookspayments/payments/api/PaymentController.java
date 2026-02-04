package com.example.msbookspayments.payments.api;


import com.example.msbookspayments.payments.api.dto.CreatePaymentRequest;
import com.example.msbookspayments.payments.api.dto.PaymentResponse;
import com.example.msbookspayments.payments.application.PaymentService;
import com.example.msbookspayments.payments.domain.Payment;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> create(@Valid @RequestBody CreatePaymentRequest request) {
        Payment payment = paymentService.createPayment(
                request.bookId(),
                request.units(),
                request.buyerEmail()
                );

        return ResponseEntity.status(HttpStatus.CREATED).body(PaymentResponse.from(payment));
    }

}
