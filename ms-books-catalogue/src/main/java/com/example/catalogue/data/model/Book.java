package com.example.catalogue.data.model;

import com.example.catalogue.controller.model.BookDto;
import com.example.catalogue.data.utils.Consts;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = Consts.TITLE, unique = false)
    private String title;

    @Column(name = Consts.AUTHOR, unique = false)
    private String author;

    @Column(name = Consts.PUBLICATION_DATE, unique = false)
    private Date publicationDate;

    @Column(name = Consts.ISBN_CODE, unique = true)
    private String isbnCode;

    @Column(name = Consts.CATEGORY, unique = false)
    private String category;

    @Column(name = Consts.VALORATION, unique = false)
    private Integer valoration;

    @Column(name = Consts.VISIBILITY, unique = false)
    private Boolean visibility;

    @Column(name = Consts.STOCK, unique = false)
    private Integer stock;


    public void update(BookDto bookDto){
        this.author = bookDto.getAuthor();
        this.isbnCode = bookDto.getIsbnCode();
        this.publicationDate = bookDto.getPublicationDate();
        this.title = bookDto.getTitle();
        this.category = bookDto.getCategory();
        this.valoration = bookDto.getValoration();
        this.visibility = bookDto.getVisibility();
        this.stock = bookDto.getStock();
    }
}
