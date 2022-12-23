package com.quid.kopring.user

import com.quid.kopring.user.repository.UserJpaRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.transaction.Transactional


@SpringBootTest
@Transactional
class UserJpaRepositoryTest @Autowired constructor(
    private val userRepository: UserJpaRepository
) {
    @Test
    fun fetchTest(){
        val histories: List<User> = userRepository.findAllWithUserLoanHistories()
        println(histories)
    }
}