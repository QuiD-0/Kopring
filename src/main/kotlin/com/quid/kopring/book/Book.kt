package com.quid.kopring.book

import com.quid.kopring.book.model.request.BookUpdateRequest
import com.quid.kopring.book.model.type.BookType
import javax.persistence.*

@Entity
class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String,
    @Enumerated(EnumType.STRING)
    var type: BookType,
) {
    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("name must not be blank")
        }
    }

    fun update(request: BookUpdateRequest) {
        name = request.name
        type = request.type
    }

}