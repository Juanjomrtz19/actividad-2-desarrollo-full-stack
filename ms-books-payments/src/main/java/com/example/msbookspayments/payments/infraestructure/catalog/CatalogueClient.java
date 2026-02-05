package com.example.msbookspayments.payments.infraestructure.catalog;

import com.example.msbookspayments.payments.application.exception.CatalogueBadGatewayException;
import com.example.msbookspayments.payments.application.exception.CatalogueBookNotFoundException;
import com.example.msbookspayments.payments.infraestructure.catalog.dto.BookDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Component
public class CatalogueClient {
    private final RestTemplate restTemplate;

    public CatalogueClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public BookDto getBookById(Long bookId){
        try {
            return restTemplate.getForObject(
                    "http://ms-books-catalogue/books/{id}",
                    BookDto.class,
                    bookId
            );
        } catch (HttpStatusCodeException exception){
            HttpStatusCode code = exception.getStatusCode();

            if (code.value()==400) throw new CatalogueBookNotFoundException(bookId);

            throw new CatalogueBadGatewayException("Catalogue error: " + code.value());
        } catch (Exception exception){
            throw   new CatalogueBadGatewayException("Catalogue not reachable");
        }
    }
}
