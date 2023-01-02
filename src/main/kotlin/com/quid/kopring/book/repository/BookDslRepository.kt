package com.quid.kopring.book.repository

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import com.quid.kopring.book.QBook.book
import com.quid.kopring.book.model.response.BookStat
import org.springframework.stereotype.Repository

@Repository
class BookDslRepository(
    private val jpaQueryFactory: JPAQueryFactory
) {
    fun getBookStat() : List<BookStat> {
        return jpaQueryFactory.select(
            Projections.constructor(
                BookStat::class.java,
                book.type,
                book.count()
            )
        )
            .from(book)
            .groupBy(book.type)
            .fetch()
    }
}