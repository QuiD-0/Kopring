package com.quid.kopring.service.user

import com.quid.kopring.book.Book
import com.quid.kopring.domain.user.UserRepository
import com.quid.kopring.dto.user.request.UserCreateRequest
import com.quid.kopring.dto.user.request.UserUpdateRequest
import com.quid.kopring.user.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.transaction.Transactional

@SpringBootTest
@Transactional
class UserServiceTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val userService: UserService
) {

    companion object {
        const val NAME: String = "test"
        const val UPDATEDNAME : String = "updated"
    }

    @Test
    @DisplayName("유저 생성")
    fun saveUser() {
        val request = UserCreateRequest(NAME, 10)

        userService.saveUser(request)

        val user = userRepository.findByName(NAME).get().also { println("name : ${it.name}") }
        assertThat(user.name).isEqualTo(NAME)
    }

    @Test
    @DisplayName("유저 이름 수정")
    fun updateUser() {
        val request = UserCreateRequest(NAME, 10)
        userService.saveUser(request)

        val user = userRepository.findByName(NAME).get()

        val updateRequest = UserUpdateRequest(user.id ?: 0L, UPDATEDNAME)
        userService.updateUserName(updateRequest)

        val updatedUser = userRepository.findByName(UPDATEDNAME).get()
        assertThat(updatedUser.name).isEqualTo(UPDATEDNAME)
    }

    @Test
    @DisplayName("유저 삭제")
    fun deleteUser() {
        val request = UserCreateRequest(NAME, 10)
        userService.saveUser(request)

        val user = userRepository.findByName(NAME).get()

        userService.deleteUser(user.name)

        val deletedUser = userRepository.findByName(NAME)
        assertThat(deletedUser).isEmpty
    }

    @Test
    @DisplayName("대출 테스트")
    fun loanBook() {
        val user = User(name = NAME, age =  10)
        val book = Book(name = "testBook")

        user.loanBook(book)

        assertThat(user.userLoanHistories.size).isEqualTo(1)
    }

}