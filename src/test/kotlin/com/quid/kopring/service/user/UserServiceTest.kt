package com.quid.kopring.service.user

import com.quid.kopring.domain.user.UserRepository
import com.quid.kopring.dto.user.request.UserCreateRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val userService: UserService) {

    @Test
    fun saveUser(){
        val request = UserCreateRequest("test",10)

        userService.saveUser(request)

        val user = userRepository.findByName("test").get()
        assertThat(user.name).isEqualTo("test")
    }

}