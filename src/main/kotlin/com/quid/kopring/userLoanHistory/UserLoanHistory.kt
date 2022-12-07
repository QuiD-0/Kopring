package com.quid.kopring.userLoanHistory

import com.quid.kopring.domain.user.User
import javax.persistence.*

@Entity
class UserLoanHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne
    val user: User,
    val bookName: String,
    var isReturn: Boolean,
) {
    fun doReturn() {
        this.isReturn = true
    }
}