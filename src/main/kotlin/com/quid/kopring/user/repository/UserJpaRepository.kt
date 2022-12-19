package com.quid.kopring.user.repository

import com.quid.kopring.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository : JpaRepository<User, Long> {
    fun findByName(name: String): User?
}