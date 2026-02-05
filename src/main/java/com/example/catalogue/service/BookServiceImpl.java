package com.example.catalogue.service;

import com.example.catalogue.controller.model.BookDto;
import com.example.catalogue.controller.model.CreateBookRequest;
import com.example.catalogue.data.BookRepository;
import com.example.catalogue.data.model.Book;
import com.example.catalogue.data.utils.Consts;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class BookServiceImpl implements BooksService{
    @Autowired
    private BookRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    private boolean isValidDate(String dateStr) {
        if (dateStr == null) return false;
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public List<Book> getBooks(String title, String author, String isbnCode, String category, String publicationDate, Integer valoration, Boolean visibility, Integer stock) {

        Date dateParsed = null;

        if (StringUtils.hasLength(publicationDate)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                dateParsed = sdf.parse(publicationDate);
            } catch (ParseException e) {
            }
        }

        if (StringUtils.hasLength(title) || StringUtils.hasLength(author) || StringUtils.hasLength(isbnCode)
                || StringUtils.hasLength(category) || valoration != null || visibility != null || dateParsed != null
                || stock != null) {

            return repository.search(title, author, isbnCode, category, dateParsed, valoration, visibility, stock);
        }

        List<Book> books = repository.getBooks();
        return books.isEmpty() ? null : books;
    }

    @Override
    public Book getBook(String bookId) { return repository.getById(Long.valueOf(bookId)); }

    @Override
    public Boolean removeBook(String bookId){
        Book book = repository.getById(Long.valueOf(bookId));
        if(book != null){
            repository.delete(book);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Book createBook(CreateBookRequest request){
        boolean isValid = StringUtils.hasText(request.getTitle())
                && StringUtils.hasText(request.getAuthor())
                && StringUtils.hasText(request.getIsbnCode())
                && StringUtils.hasText(request.getCategory())
                && request.getVisibility() != null
                && request.getValoration() != null
                && Integer.parseInt(request.getValoration()) >= Consts.MIN_VALORATION
                && Integer.parseInt(request.getValoration()) <= Consts.MAX_VALORATION
                && this.isValidDate(request.getPublicationDate())
                && request.getStock() != null;

        if (isValid) {

            Date dateParsed = null;

            if (StringUtils.hasLength(request.getPublicationDate())) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    dateParsed = sdf.parse(request.getPublicationDate());
                } catch (ParseException e) {
                }
            }

            Book book = Book.builder().title(request.getTitle()).author(request.getAuthor()).isbnCode(request.getIsbnCode())
                    .category(request.getCategory()).visibility(request.getVisibility()).valoration(request.getValoration())
                    .publicationDate(dateParsed).stock(request.getStock()).build();

            return repository.save(book);
        } else {
            return null;
        }
    }

    @Override
    public Book updateBook(String bookId, String request){
        Book book = repository.getById(Long.valueOf(bookId));
        if(book != null) {
            try {
                JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(request));
                JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(book)));
                Book patched = objectMapper.treeToValue(target, Book.class);
                repository.save(patched);
                return patched;
            } catch (JsonProcessingException | JsonPatchException e){
                log.error("Error updating product {}", bookId, e);
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public Book updateBook(String bookId, BookDto updateRequest){
        Book book = repository.getById(Long.valueOf(bookId));
        if(book != null){
            book.update(updateRequest);
            repository.save(book);
            return book;
        } else {
            return null;
        }
    }
}
