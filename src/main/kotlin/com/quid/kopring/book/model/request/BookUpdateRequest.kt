package com.quid.kopring.book.model.request

import com.quid.kopring.book.model.type.BookType

data class BookUpdateRequest (val name: String, val type: BookType){
}