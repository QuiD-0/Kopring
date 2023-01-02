package com.quid.kopring.book.repository

import com.quid.kopring.book.Book
import com.quid.kopring.book.model.response.BookStat
import org.springframework.stereotype.Repository

@Repository
class BookRepositoryImpl(
    private val bookJpaRepository: BookJpaRepository,
    private val bookDslRepository: BookDslRepository
) : BookRepository {
    override fun findByName(name: String): Book? {
        return bookJpaRepository.findByName(name)
    }

    override fun getBookStat(): List<BookStat> {
        return bookDslRepository.getBookStat()
    }

    override fun findById(id: Long): Book? {
        return bookJpaRepository.findById(id).orElse(null)
    }

    override fun findAll(): List<Book> {
        return bookJpaRepository.findAll()
    }

    override fun save(it: Book) {
        bookJpaRepository.save(it)
    }
}
