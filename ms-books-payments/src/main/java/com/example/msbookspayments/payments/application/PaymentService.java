package com.example.msbookspayments.payments.application;

import com.example.msbookspayments.payments.application.exception.BookHiddenException;
import com.example.msbookspayments.payments.application.exception.OutOfStockException;
import com.example.msbookspayments.payments.domain.Payment;
import com.example.msbookspayments.payments.infraestructure.catalog.CatalogueClient;
import com.example.msbookspayments.payments.infraestructure.catalog.dto.BookDto;
import com.example.msbookspayments.payments.infrastructure.persistence.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final CatalogueClient catalogueClient;

    public PaymentService(PaymentRepository paymentRepository, CatalogueClient catalogueClient){
        this.paymentRepository = paymentRepository;
        this.catalogueClient = catalogueClient;
    }

    @Transactional
    public Payment createPayment(Long bookId, Integer units, String buyerEmail){

        BookDto book = catalogueClient.getBookById(bookId);

        if (Boolean.FALSE.equals(book.visible())){
            throw new BookHiddenException(bookId);
        }

        int stock = (book.stock() != null)
                ? book.stock()
                : simulatedStock(bookId);

        if (units > stock){
            throw new OutOfStockException(bookId);
        }

        Payment payment = Payment.confirmed(bookId, units, buyerEmail);
        return paymentRepository.save(payment);
    }

    private int simulatedStock(Long bookId){
        return (bookId % 2 == 0) ? 0 : 10;
    }
}
