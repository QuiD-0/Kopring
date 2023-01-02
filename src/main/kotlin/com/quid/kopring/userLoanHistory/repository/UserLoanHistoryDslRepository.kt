package com.quid.kopring.userLoanHistory.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.quid.kopring.userLoanHistory.QUserLoanHistory.userLoanHistory
import com.quid.kopring.userLoanHistory.type.UserLoanStatus
import org.springframework.stereotype.Repository

@Repository
class UserLoanHistoryDslRepository(
    private val queryFactory: JPAQueryFactory
) {
    fun findByBookNameAndStatus(bookName: String, status: UserLoanStatus) = queryFactory
        .selectFrom(userLoanHistory)
        .where(userLoanHistory.bookName.eq(bookName), userLoanHistory.status.eq(status))
        .fetchOne()

    fun findByStatus(status: UserLoanStatus) = queryFactory
        .selectFrom(userLoanHistory)
        .where(userLoanHistory.status.eq(status))
        .fetch()
}