package com.quid.kopring.userLoanHistory.repository

import com.quid.kopring.userLoanHistory.UserLoanHistory
import com.quid.kopring.userLoanHistory.type.UserLoanStatus
import org.springframework.data.jpa.repository.JpaRepository

interface UserLoanHistoryJpaRepository: JpaRepository<UserLoanHistory, Long> {
    fun findByBookNameAndStatus(bookName: String, status: UserLoanStatus): UserLoanHistory?

    fun findByStatus(status: UserLoanStatus) : List<UserLoanHistory>
}