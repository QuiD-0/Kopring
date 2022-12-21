package com.quid.kopring.book

import com.quid.kopring.book.model.type.BookType
import javax.persistence.*

@Entity
class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    @Enumerated(EnumType.STRING)
    val type: BookType,
) {
    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("name must not be blank")
        }
    }

}