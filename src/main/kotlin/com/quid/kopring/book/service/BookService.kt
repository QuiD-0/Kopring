package com.quid.kopring.book.service

import com.quid.kopring.book.Book
import com.quid.kopring.book.model.request.BookCreateRequest
import com.quid.kopring.book.model.request.BookLoanRequest
import com.quid.kopring.book.model.request.BookReturnRequest
import com.quid.kopring.book.model.request.BookUpdateRequest
import com.quid.kopring.book.model.response.BookStat
import com.quid.kopring.book.repository.BookJpaRepository
import com.quid.kopring.user.repository.UserJpaRepository
import com.quid.kopring.userLoanHistory.repository.UserLoanHistoryJpaRepository
import com.quid.kopring.userLoanHistory.type.UserLoanStatus
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
    fun saveBook(request: BookCreateRequest) {
        Book(name = request.name, type = request.type).also { bookJpaRepository.save(it) }
    }

    @Transactional
    fun loanBook(request: BookLoanRequest) {
        val book = bookJpaRepository.findByName(request.bookName) ?: fail("Book not found")

        if (userLoanHistoryRepository.findByBookNameAndStatus(
                request.bookName, UserLoanStatus.LOANED
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

    @Transactional(readOnly = true)
    fun getBooks(): List<Book> {
        return bookJpaRepository.findAll()
    }

    @Transactional(readOnly = true)
    fun getCountLoanBook(): Int {
        return userLoanHistoryRepository.findByStatus(UserLoanStatus.LOANED).size
    }

    @Transactional(readOnly = true)
    fun getStat(): List<BookStat> {
        return bookJpaRepository.findAll()
            .groupBy { it.type }
            .map { (type, books) -> BookStat(type = type, count = books.size) }
    }

    @Transactional
    fun updateBook(id: Long, request: BookUpdateRequest) {
        bookJpaRepository.findById(id).orElseThrow { fail("Book not found") }.also {
            it.update(request)
        }
    }
}