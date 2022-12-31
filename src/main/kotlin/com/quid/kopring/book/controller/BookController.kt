package com.quid.kopring.book.controller

import com.quid.kopring.book.Book
import com.quid.kopring.book.model.request.BookLoanRequest
import com.quid.kopring.book.model.request.BookCreateRequest
import com.quid.kopring.book.model.request.BookReturnRequest
import com.quid.kopring.book.model.request.BookUpdateRequest
import com.quid.kopring.book.model.response.BookStat
import com.quid.kopring.book.service.BookService
import org.springframework.web.bind.annotation.*

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

    @GetMapping("/loan")
    fun getCountLoanBook(): Int {
        return bookService.getCountLoanBook()
    }

    @PostMapping("/loan")
    fun loanBook(@RequestBody request: BookLoanRequest) {
        bookService.loanBook(request)
    }

    @PutMapping("/return")
    fun returnBook(@RequestBody request: BookReturnRequest) {
        bookService.returnBook(request)
    }

    @GetMapping("/stat")
    fun getStat(): List<BookStat> {
        return bookService.getStat()
    }

    @PutMapping("/{id}")
    fun updateBook(@PathVariable id: Long, @RequestBody request: BookUpdateRequest) {
        bookService.updateBook(id, request)
    }
}