package com.quid.kopring.book.model.response

import com.quid.kopring.book.model.type.BookType

data class BookStat (val bookType: BookType, val count: Int){

}