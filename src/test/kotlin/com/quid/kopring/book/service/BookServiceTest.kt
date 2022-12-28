package com.quid.kopring.book.service

import com.quid.kopring.book.model.request.BookCreateRequest
import com.quid.kopring.book.model.type.BookType
import com.quid.kopring.book.model.type.BookType.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BookServiceTest @Autowired constructor(
    private val bookService: BookService
) {

    @Test
    fun statTest() {
        BookCreateRequest("test", SCIENCE).let { bookService.saveBook(it) }

        val stat = bookService.getStat()

        assert(stat.size == 1)
    }
}