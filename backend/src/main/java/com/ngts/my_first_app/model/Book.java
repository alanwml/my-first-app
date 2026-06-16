package com.ngts.my_first_app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "books")
public class Book {

    @Id // This is the Primary Key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    private String author;
}
