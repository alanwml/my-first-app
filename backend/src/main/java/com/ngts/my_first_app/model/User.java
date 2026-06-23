package com.ngts.my_first_app.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id // This means the Primary Key (Unique Identifier)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;
    private int age;

    // Email should be Unique to prevent same email Writes.
    @Column(name = "email", unique = true)
    private String email;

    // registrationDate will be auto-created.
    @CreationTimestamp
    private LocalDateTime registrationDate;
}
