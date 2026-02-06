package com.example.catalogue.data;

import com.example.catalogue.data.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;

interface BookJpaRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

    List<Book> findByPublicationDate(Date publicationDate);

    List<Book> findByValoration(Integer valoration);

    List<Book> findByVisibility(Boolean visibility);

    List<Book> findByCategory(String category);

    List<Book> findByIsbnCode(String isbnCode);

    List<Book> findByStock(Integer stock);

    List<Book> findByAuthorAndTitle(String author, String title);
}
