package com.example.msbookspayments.payments.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name="payments")

public class Payment {
    @Id
    @Column(length = 36, nullable = false, updatable = false)
    private String id;

    @Column(nullable = false, updatable = false)
    private Long bookId;

    @Column(nullable = false, updatable = false)
    private Integer units;

    @Column(nullable = false, updatable = false)
    private String buyerEmail;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    protected Payment(){

    }

    private Payment(Long bookId, Integer units, String buyerEmail){
        this.id = UUID.randomUUID().toString();
        this.bookId = bookId;
        this.units = units;
        this.buyerEmail = buyerEmail;
        this.status = PaymentStatus.CONFIRMED;
        this.createdAt = Instant.now();
    }

    public static  Payment confirmed(Long bookId, Integer units, String buyerEmail){
        return new Payment(bookId, units, buyerEmail);
    }

    public void reject(){
        this.status = PaymentStatus.REJECTED;
    }

    public String getId(){
        return id;
    }

    public Long getBookId(){
        return bookId;
    }

    public Integer getUnits(){
        return units;
    }

    public String getBuyerEmail(){
        return buyerEmail;
    }

    public PaymentStatus getStatus(){
        return status;
    }

    public Instant getCreatedAt(){
        return createdAt;
    }


}
