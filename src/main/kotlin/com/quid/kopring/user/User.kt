package com.quid.kopring.user

import com.quid.kopring.book.Book
import com.quid.kopring.user.model.request.UserCreateRequest
import com.quid.kopring.userLoanHistory.UserLoanHistory
import com.quid.kopring.userLoanHistory.type.UserLoanStatus.LOANED
import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY

@Entity
class User(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long? = null,
    var name: String,
    var age: Int,
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val userLoanHistories: MutableList<UserLoanHistory> = mutableListOf(),
) {
    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("Name cannot be blank")
        }
    }

    companion object {
        fun of(request: UserCreateRequest): User {
            return User(
                name = request.name,
                age = request.age
            )
        }
    }

    fun updateName(name: String) {
        this.name = name
    }

    fun loanBook(book: Book) {
        UserLoanHistory(
            user = this,
            bookName = book.name
        ).also { userLoanHistories.add(it) }
    }

    fun returnBook(bookName: String) {
        userLoanHistories.find { it.bookName == bookName && it.status == LOANED }?.doReturn()
            ?: throw IllegalArgumentException("존재하지 않는 책입니다.")
    }
}