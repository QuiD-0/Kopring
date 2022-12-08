package com.quid.kopring.userLoanHistory

import com.quid.kopring.user.User
import javax.persistence.*

@Entity
class UserLoanHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne
    val user: User,
    val bookName: String,
    var isReturn: Boolean = false,
) {
    fun doReturn() {
        this.isReturn = true
    }
}