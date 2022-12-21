package com.quid.kopring.book.service

import com.quid.kopring.book.Book
import com.quid.kopring.book.repository.BookJpaRepository
import com.quid.kopring.book.model.request.BookLoanRequest
import com.quid.kopring.book.model.request.BookRequest
import com.quid.kopring.book.model.request.BookReturnRequest
import com.quid.kopring.user.repository.UserJpaRepository
import com.quid.kopring.userLoanHistory.repository.UserLoanHistoryJpaRepository
import com.quid.kopring.util.fail
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService(
    private val bookJpaRepository: BookJpaRepository,
    private val userJpaRepository: UserJpaRepository,
    private val userLoanHistoryRepository: UserLoanHistoryJpaRepository
) {
    @Transactional
    fun saveBook(request: BookRequest) {
        Book(name = request.name, type = request.type).also { bookJpaRepository.save(it) }
    }

    @Transactional
    fun loanBook(request: BookLoanRequest) {
        val book = bookJpaRepository.findByName(request.bookName) ?: fail("Book not found")

        if (userLoanHistoryRepository.findByBookNameAndIsReturn(
                request.bookName, false
            ) != null
        ) fail("Book is already loaned")

        val user = userJpaRepository.findByName(request.userName) ?: fail("User not found")
        user.loanBook(book)
    }

    @Transactional
    fun returnBook(request: BookReturnRequest) {
        userJpaRepository.findByName(request.userName)?.returnBook(request.bookName)
            ?: fail("User not found")
    }

    fun getBooks(): List<Book> {
        return bookJpaRepository.findAll();
    }
}