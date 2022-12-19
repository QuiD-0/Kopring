package com.quid.kopring.user.repository

import com.quid.kopring.user.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserJpaRepository: JpaRepository<User, Long> {
    fun findByName(name: String): Optional<User>
}