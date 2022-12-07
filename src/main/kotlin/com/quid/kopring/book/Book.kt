package com.quid.kopring.book

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Book(
    @Id val id: Long? = null,
    val name: String,
) {
    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("name must not be blank")
        }

    }

}