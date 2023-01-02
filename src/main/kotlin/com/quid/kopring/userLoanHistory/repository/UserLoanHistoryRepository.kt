package com.quid.kopring.userLoanHistory.repository

import com.quid.kopring.userLoanHistory.UserLoanHistory
import com.quid.kopring.userLoanHistory.type.UserLoanStatus

interface UserLoanHistoryRepository {
    fun findByBookNameAndStatus(bookName: String, status: UserLoanStatus): UserLoanHistory?

    fun findByStatus(status: UserLoanStatus) : List<UserLoanHistory>
}