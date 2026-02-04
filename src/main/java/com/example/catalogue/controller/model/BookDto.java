package com.example.catalogue.controller.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BookDto {
    private String title;
    private String author;
    private Date publicationDate;
    private String isbnCode;
    private String category;
    private String valoration;
    private String visibility;
}
