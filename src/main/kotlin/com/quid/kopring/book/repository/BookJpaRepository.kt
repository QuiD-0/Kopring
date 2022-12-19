package com.quid.kopring.book.repository

import com.quid.kopring.book.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookJpaRepository : JpaRepository<Book, Long> {
    fun findByName(name: String): Book?
}