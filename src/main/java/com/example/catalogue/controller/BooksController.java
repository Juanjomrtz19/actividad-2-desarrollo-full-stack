package com.example.catalogue.controller;

import com.example.catalogue.controller.model.BookDto;
import com.example.catalogue.controller.model.CreateBookRequest;
import com.example.catalogue.data.model.Book;
import com.example.catalogue.service.BooksService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BooksController {

    private final BooksService service;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(
            @RequestHeader Map<String, String> headers,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String isbnCode,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer valoration ,
            @RequestParam(required = false) Boolean visibility,
            @RequestParam(required = false) String publicationDate,
            @RequestParam(required = false) Integer stock
    ){
        log.info("headers: {}", headers);
        List<Book> books = service.getBooks(title, author, isbnCode, category, publicationDate, valoration, visibility, stock);
        if (books != null){
            return ResponseEntity.ok(books);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable String bookId){
        log.info("Request received for book {}", bookId);
        Book book = service.getBook(bookId);

        if(book != null){
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("books/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable String bookId){
        Boolean removed = service.removeBook(bookId);

        if(Boolean.TRUE.equals(removed)){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBooks(@RequestBody CreateBookRequest request){
        Book createdBook = service.createBook(request);

        if(createdBook != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/books/{bookId}")
    public ResponseEntity<Book> patchBook(@PathVariable String bookId, @RequestBody String patchBody){
        Book patched = service.updateBook(bookId, patchBody);
        if(patched != null){
            return ResponseEntity.ok(patched);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<Book> putBook(@PathVariable String bookId, @RequestBody BookDto putBody){
        Book updated = service.updateBook(bookId, putBody);
        if(updated != null){
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


}
