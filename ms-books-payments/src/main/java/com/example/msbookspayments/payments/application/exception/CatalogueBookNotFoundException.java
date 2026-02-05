package com.example.msbookspayments.payments.application.exception;

public class CatalogueBookNotFoundException extends RuntimeException{

    public CatalogueBookNotFoundException(Long bookId){
        super("Book not found in catalogue: " + bookId);
    }
}
