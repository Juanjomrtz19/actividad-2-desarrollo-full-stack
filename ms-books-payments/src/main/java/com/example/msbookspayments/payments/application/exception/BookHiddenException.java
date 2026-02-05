package com.example.msbookspayments.payments.application.exception;

public class BookHiddenException extends RuntimeException {
    public BookHiddenException(Long bookId){
        super("Book is hidden: " + bookId);
    }

}
