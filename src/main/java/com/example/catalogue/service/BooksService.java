package com.example.catalogue.service;

import com.example.catalogue.controller.model.BookDto;
import com.example.catalogue.controller.model.CreateBookRequest;
import com.example.catalogue.data.model.Book;

import java.util.Date;
import java.util.List;

public interface BooksService {
    List<Book> getBooks(String title, String author, String isbnCode, String category, String publicationDate, Integer valoration,
                        Boolean visibility, Integer stock);

    Book getBook(String bookId);

    Boolean removeBook(String bookId);

    Book createBook(CreateBookRequest request);

    Book updateBook(String bookId, String updateRequest);

    Book updateBook(String bookId, BookDto updateRequest);
}
