package com.quid.kopring.service.user

import com.quid.kopring.domain.user.UserRepository
import com.quid.kopring.dto.user.request.UserCreateRequest
import com.quid.kopring.dto.user.request.UserUpdateRequest
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
        const val UPDATED_NAME : String = "updated"
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

        val updateRequest = UserUpdateRequest(user.id ?: 0L, UPDATED_NAME)
        userService.updateUserName(updateRequest)

        val updatedUser = userRepository.findByName(UPDATED_NAME).get()
        assertThat(updatedUser.name).isEqualTo(UPDATED_NAME)
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

}