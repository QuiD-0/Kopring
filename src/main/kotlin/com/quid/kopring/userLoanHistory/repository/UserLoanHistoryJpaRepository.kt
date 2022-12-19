package com.quid.kopring.userLoanHistory.repository

import com.quid.kopring.userLoanHistory.UserLoanHistory
import org.springframework.data.jpa.repository.JpaRepository

interface UserLoanHistoryJpaRepository: JpaRepository<UserLoanHistory, Long> {
    fun findByBookNameAndIsReturn(bookName: String, isReturn: Boolean): UserLoanHistory?
}