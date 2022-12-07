package com.quid.kopring.domain.book;

import com.quid.kopring.book.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByName(String bookName);

}
