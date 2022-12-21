package com.quid.kopring.userLoanHistory

import com.quid.kopring.user.User
import com.quid.kopring.userLoanHistory.type.UserLoanStatus
import com.quid.kopring.userLoanHistory.type.UserLoanStatus.*
import javax.persistence.*

@Entity
class UserLoanHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne
    val user: User,
    val bookName: String,
    var status: UserLoanStatus = LOANED,
) {
    fun doReturn() {
        this.status = RETURNED
    }
}