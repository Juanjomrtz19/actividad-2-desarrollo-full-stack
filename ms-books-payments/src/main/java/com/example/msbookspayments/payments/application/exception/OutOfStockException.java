package com.example.msbookspayments.payments.application.exception;

public class OutOfStockException extends RuntimeException{
    public OutOfStockException(Long bookId){
        super("Out of stock of book: " + bookId );
    }
}
