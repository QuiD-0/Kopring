package com.quid.kopring.user

import com.quid.kopring.book.Book
import com.quid.kopring.book.model.type.BookType.COMPUTER
import com.quid.kopring.service.user.UserServiceTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class UserTest {

    val book = Book(name = "testBook", type = COMPUTER)

    @Test
    @DisplayName("대출 테스트")
    fun loanBook() {
        val user = User(name = UserServiceTest.NAME, age = 10)

        user.loanBook(book)

        Assertions.assertThat(user.userLoanHistories.size).isEqualTo(1)
    }

    @Test
    @DisplayName("반납 테스트")
    fun returnBook() {
        val user = User(name = UserServiceTest.NAME, age = 10)

        user.loanBook(book)
        user.returnBook(book.name)

        Assertions.assertThat(user.userLoanHistories.find { it.bookName == book.name }?.status)
            .isEqualTo(true)
    }

}