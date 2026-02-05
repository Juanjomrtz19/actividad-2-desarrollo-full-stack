package com.example.msbookspayments.payments.common.error;

import com.example.msbookspayments.payments.application.exception.BookHiddenException;
import com.example.msbookspayments.payments.application.exception.CatalogueBadGatewayException;
import com.example.msbookspayments.payments.application.exception.CatalogueBookNotFoundException;
import com.example.msbookspayments.payments.application.exception.OutOfStockException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(CatalogueBookNotFoundException.class)
    public ResponseEntity<ApiError> bookNotFound(CatalogueBookNotFoundException ex){
        return ResponseEntity.status(404).body(ApiError.of("BOOK_NOT_FOUND", ex.getMessage()));
    }
    @ExceptionHandler(BookHiddenException.class)
    public ResponseEntity<ApiError> bookHidden(BookHiddenException ex) {
        return ResponseEntity.status(409).body(ApiError.of("BOOK_HIDDEN", ex.getMessage()));
    }

    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<ApiError> outOfStock(OutOfStockException ex) {
        return ResponseEntity.status(409).body(ApiError.of("OUT_OF_STOCK", ex.getMessage()));
    }

    @ExceptionHandler(CatalogueBadGatewayException.class)
    public ResponseEntity<ApiError> catalogueDown(CatalogueBadGatewayException ex) {
        return ResponseEntity.status(502).body(ApiError.of("CATALOGUE_UNAVAILABLE", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> validation(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(ApiError.of("VALIDATION_ERROR", "Solicitud inválida"));
    }
}
