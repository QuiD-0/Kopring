package com.quid.kopring.book.service

import com.quid.kopring.book.model.request.BookCreateRequest
import com.quid.kopring.book.model.request.BookLoanRequest
import com.quid.kopring.book.model.request.BookUpdateRequest
import com.quid.kopring.book.model.type.BookType.COMPUTER
import com.quid.kopring.book.model.type.BookType.SCIENCE
import com.quid.kopring.user.model.request.UserCreateRequest
import com.quid.kopring.user.service.UserService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class BookServiceTest @Autowired constructor(
    private val bookService: BookService,
    private val userService: UserService,
) {

    companion object {
        val create = BookCreateRequest("test", SCIENCE)
        val create2 = BookCreateRequest("test2", SCIENCE)
        val create3 = BookCreateRequest("test3", COMPUTER)
        val loan = BookLoanRequest("user1", "test")
        val userCreate = UserCreateRequest("user1", 20)
    }

    @Test
    @Transactional
    fun statTest() {
        create.let { bookService.saveBook(it) }
        create2.let { bookService.saveBook(it) }
        create3.let { bookService.saveBook(it) }

        val stat = bookService.getStat()

        assert(stat.size == 2)
    }

    @Test
    @Transactional
    fun loanTest() {
        create.let { bookService.saveBook(it) }
        userCreate.let { userService.saveUser(it) }

        bookService.loanBook(loan)

        assert(userService.getUserLoanList("user1").loanList.isNotEmpty())
    }

    @Test
    @Transactional
    fun updateTest() {
        create.let(bookService::saveBook)

        bookService.updateBook(1L, BookUpdateRequest("updated", SCIENCE))

        val book = bookService.getBooks().first()

        assert(book.name == "updated")
    }

}