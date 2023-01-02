package com.quid.kopring.book.repository

import com.quid.kopring.book.Book
import com.quid.kopring.book.model.response.BookStat
import org.springframework.stereotype.Repository

@Repository
interface BookRepository {
    fun findByName(name: String): Book?
    fun getBookStat(): List<BookStat>
    fun findById(id: Long): Book?
    fun findAll(): List<Book>
    fun save(it: Book)
}