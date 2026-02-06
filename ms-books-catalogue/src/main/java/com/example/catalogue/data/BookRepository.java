package com.example.catalogue.data;

import com.example.catalogue.data.model.Book;
import com.example.catalogue.data.utils.Consts;
import com.example.catalogue.data.utils.SearchCriteria;
import com.example.catalogue.data.utils.SearchOperation;
import com.example.catalogue.data.utils.SearchStatement;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {
    private final BookJpaRepository repository;

    public List<Book> getBooks() { return repository.findAll(); }

    public Book getById(Long id) { return repository.findById(id).orElse(null); }

    public Book save(Book book) { return repository.save(book); }

    public void delete(Book book) { repository.delete(book); }

    public List<Book> search(String title, String author, String isbnCode, String category, Date publicationDate, Integer valoration, Boolean visibility, Integer stock){
        SearchCriteria<Book> spec = new SearchCriteria<>();

        if(StringUtils.isNotBlank(title)){
            spec.add(new SearchStatement(Consts.TITLE, title, SearchOperation.MATCH));
        }

        if(StringUtils.isNotBlank(author)){
            spec.add(new SearchStatement(Consts.AUTHOR, author, SearchOperation.MATCH));
        }

        if(StringUtils.isNotBlank(isbnCode)){
            spec.add(new SearchStatement(Consts.ISBN_CODE, isbnCode, SearchOperation.EQUAL));
        }

        if(StringUtils.isNotBlank(category)){
            spec.add(new SearchStatement(Consts.CATEGORY, category, SearchOperation.EQUAL));
        }

        if(valoration != null) {
            spec.add(new SearchStatement(Consts.VALORATION, valoration, SearchOperation.GREATER_THAN_EQUAL));
        }

        if (publicationDate != null) {
            spec.add(new SearchStatement(Consts.PUBLICATION_DATE, publicationDate, SearchOperation.GREATER_THAN_EQUAL));
        }

        if(visibility != null){
            spec.add(new SearchStatement(Consts.VISIBILITY, visibility, SearchOperation.EQUAL));
        }

        if(stock != null){
            spec.add(new SearchStatement(Consts.STOCK, stock, SearchOperation.EQUAL));
        }

        return repository.findAll(spec);
    }
}
