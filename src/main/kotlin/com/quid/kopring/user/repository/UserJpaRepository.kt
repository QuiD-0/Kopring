package com.quid.kopring.user.repository

import com.quid.kopring.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserJpaRepository : JpaRepository<User, Long> {
    fun findByName(name: String): User?

    @Query("select distinct u from User u left join fetch u.userLoanHistories")
    fun findAllWithUserLoanHistories(): List<User>
}