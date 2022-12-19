package com.quid.kopring.book.service

import com.quid.kopring.book.Book
import com.quid.kopring.book.repository.BookJpaRepository
import com.quid.kopring.dto.book.request.BookLoanRequest
import com.quid.kopring.dto.book.request.BookRequest
import com.quid.kopring.dto.book.request.BookReturnRequest
import com.quid.kopring.user.repository.UserJpaRepository
import com.quid.kopring.userLoanHistory.repository.UserLoanHistoryJpaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService(
    private val bookJpaRepository: BookJpaRepository,
    private val userJpaRepository: UserJpaRepository,
    private val userLoanHistoryJpaRepository: UserLoanHistoryJpaRepository
) {
    @Transactional
    fun saveBook(request: BookRequest) {
        Book(name = request.name).also { bookJpaRepository.save(it) }
    }

    @Transactional
    fun loanBook(request: BookLoanRequest) {
        val book = bookJpaRepository.findByName(request.bookName)
            .orElseThrow { IllegalArgumentException("Book not found") }

        if (userLoanHistoryJpaRepository.findByBookNameAndIsReturn
                (request.bookName, false) != null
        ) throw IllegalArgumentException("Book is already loaned")

        userJpaRepository.findByName(request.userName)
            .orElseThrow { IllegalArgumentException("User not found") }
            .also { it.loanBook(book) }
    }

    @Transactional
    fun returnBook(request: BookReturnRequest){
        userJpaRepository.findByName(request.userName)
            .orElseThrow { IllegalArgumentException("User not found") }
            .also { it.returnBook(request.bookName) }
    }
}