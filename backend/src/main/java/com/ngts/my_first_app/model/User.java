package com.ngts.my_first_app.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id // This means the Primary Key (Unique Identifier)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "reg_date", nullable = false)
    private Date registrationDate;
}
