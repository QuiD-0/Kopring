package com.quid.kopring.book.repository

import com.quid.kopring.book.Book
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BookJpaRepository: JpaRepository<Book, Long> {
    fun findByName(name: String): Optional<Book>
}