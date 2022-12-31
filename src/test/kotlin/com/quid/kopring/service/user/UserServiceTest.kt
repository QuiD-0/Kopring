package com.quid.kopring.service.user

import com.quid.kopring.user.model.request.UserCreateRequest
import com.quid.kopring.user.model.request.UserUpdateRequest
import com.quid.kopring.user.repository.UserJpaRepository
import com.quid.kopring.user.service.UserService
import com.quid.kopring.userLoanHistory.repository.UserLoanHistoryJpaRepository
import com.quid.kopring.userLoanHistory.type.UserLoanStatus
import com.quid.kopring.userLoanHistory.type.UserLoanStatus.LOANED
import com.quid.kopring.util.fail
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.transaction.Transactional

@SpringBootTest
@Transactional
class UserServiceTest @Autowired constructor(
    private val userRepository: UserJpaRepository,
    private val userLoanHistoryJpaRepository: UserLoanHistoryJpaRepository,
    private val userService: UserService
) {

    companion object {
        const val NAME: String = "test"
        const val UPDATED_NAME: String = "updated"
    }

    @Test
    @DisplayName("유저 생성")
    fun saveUser() {
        val request = UserCreateRequest(NAME, 10)

        userService.saveUser(request)

        val user = userRepository.findByName(NAME)?: fail("user not found")
        assertThat(user.name).isEqualTo(NAME)
    }

    @Test
    @DisplayName("유저 이름 수정")
    fun updateUser() {
        val request = UserCreateRequest(NAME, 10)
        userService.saveUser(request)

        val user = userRepository.findByName(NAME)

        val updateRequest = UserUpdateRequest(user?.id ?: 0L, UPDATED_NAME)
        userService.updateUserName(updateRequest)

        val updatedUser = userRepository.findByName(UPDATED_NAME)?: fail("user not found")
        assertThat(updatedUser.name).isEqualTo(UPDATED_NAME)
    }

    @Test
    @DisplayName("유저 삭제")
    fun deleteUser() {
        val request = UserCreateRequest(NAME, 10)
        userService.saveUser(request)

        val user = userRepository.findByName(NAME)?: fail("user not found")

        userService.deleteUser(user.name)

        val deletedUser = userRepository.findByName(NAME)
        assertThat(deletedUser).isNull()
    }

}