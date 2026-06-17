package com.ngts.my_first_app.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "books")
public class Book {

    @Id // This is the Primary Key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "author", length = 100)
    private String author;

    @Column(name = "pub_date", nullable = false)
    private Date publlishedDate;

//    KIV
//    @Column(name = "genres")
//    private List<Genres> genres;
}
