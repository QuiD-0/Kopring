package com.quid.kopring.book.service

import com.quid.kopring.book.Book
import com.quid.kopring.book.model.request.BookCreateRequest
import com.quid.kopring.book.model.request.BookLoanRequest
import com.quid.kopring.book.model.request.BookReturnRequest
import com.quid.kopring.book.model.request.BookUpdateRequest
import com.quid.kopring.book.model.response.BookStat
import com.quid.kopring.book.repository.BookRepository
import com.quid.kopring.user.repository.UserJpaRepository
import com.quid.kopring.userLoanHistory.repository.UserLoanHistoryJpaRepository
import com.quid.kopring.userLoanHistory.type.UserLoanStatus
import com.quid.kopring.util.fail
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService(
    private val bookRepository: BookRepository,
    private val userJpaRepository: UserJpaRepository,
    private val userLoanHistoryRepository: UserLoanHistoryJpaRepository,
) {
    private val logger = KotlinLogging.logger {}

    @Transactional
    fun saveBook(request: BookCreateRequest) {
        logger.info { "saveBook request: $request" }
        Book(name = request.name, type = request.type).also { bookRepository.save(it) }
    }

    @Transactional
    fun loanBook(request: BookLoanRequest) {
        logger.info { "loanBook request: $request" }
        val book = bookRepository.findByName(request.bookName) ?: fail("Book not found")

        if (userLoanHistoryRepository.findByBookNameAndStatus(
                request.bookName, UserLoanStatus.LOANED
            ) != null
        ) fail("Book is already loaned")

        val user = userJpaRepository.findByName(request.userName) ?: fail("User not found")
        user.loanBook(book)
    }

    @Transactional
    fun returnBook(request: BookReturnRequest) {
        logger.info { "returnBook request: $request" }
        userJpaRepository.findByName(request.userName)?.returnBook(request.bookName)
            ?: fail("User not found")
    }

    @Transactional(readOnly = true)
    fun getBooks(): List<Book> {
        logger.info { "getBooks" }
        return bookRepository.findAll()
    }

    @Transactional(readOnly = true)
    fun getCountLoanBook(): Int {
        logger.info { "getCountLoanBook" }
        return userLoanHistoryRepository.findByStatus(UserLoanStatus.LOANED).size
    }

    @Transactional(readOnly = true)
    fun getStat(): List<BookStat> {
        logger.info { "getStat" }
        return bookRepository.getBookStat()
    }

    @Transactional
    fun updateBook(id: Long, request: BookUpdateRequest) {
        logger.info { "updateBook id: $id, request: $request" }
        bookRepository.findById(id)?.update(request) ?: fail("Book not found")
    }
}