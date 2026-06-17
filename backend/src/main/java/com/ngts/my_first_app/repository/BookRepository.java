package com.ngts.my_first_app.repository;

import com.ngts.my_first_app.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
