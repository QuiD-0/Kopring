package com.quid.kopring.book.controller

import com.quid.kopring.book.Book
import com.quid.kopring.book.model.request.BookLoanRequest
import com.quid.kopring.book.model.request.BookCreateRequest
import com.quid.kopring.book.model.request.BookReturnRequest
import com.quid.kopring.book.service.BookService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/book")
class BookController(
    private val bookService: BookService
) {
    @GetMapping
    fun getBooks(): List<Book> {
        return bookService.getBooks();
    }

    @PostMapping
    fun saveBook(@RequestBody request: BookCreateRequest) {
        bookService.saveBook(request)
    }

    @PostMapping("/loan")
    fun loanBook(@RequestBody request: BookLoanRequest) {
        bookService.loanBook(request)
    }

    @PutMapping("/return")
    fun returnBook(@RequestBody request: BookReturnRequest) {
        bookService.returnBook(request)
    }
}