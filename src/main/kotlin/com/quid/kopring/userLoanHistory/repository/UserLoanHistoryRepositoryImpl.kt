package com.quid.kopring.userLoanHistory.repository

import com.quid.kopring.userLoanHistory.UserLoanHistory
import com.quid.kopring.userLoanHistory.type.UserLoanStatus
import org.springframework.stereotype.Repository

@Repository
class UserLoanHistoryRepositoryImpl(
    private val userLoanHistoryJpaRepository: UserLoanHistoryJpaRepository,
    private val userLoanHistoryDslRepository: UserLoanHistoryDslRepository
) : UserLoanHistoryRepository {
    override fun findByBookNameAndStatus(
        bookName: String,
        status: UserLoanStatus
    ): UserLoanHistory? {
        return userLoanHistoryJpaRepository.findByBookNameAndStatus(bookName, status)
    }

    override fun findByStatus(status: UserLoanStatus): List<UserLoanHistory> {
        return userLoanHistoryDslRepository.findByStatus(status)
    }

}