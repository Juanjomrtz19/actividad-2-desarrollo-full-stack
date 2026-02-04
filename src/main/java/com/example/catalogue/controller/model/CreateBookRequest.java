package com.example.catalogue.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateBookRequest {
    private String title;
    private String author;
    private Date publicationDate;
    private String isbnCode;
    private String category;
    private String valoration;
    private String visibility;
}
