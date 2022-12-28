package com.quid.kopring.user.service

import com.quid.kopring.user.model.request.UserCreateRequest
import com.quid.kopring.user.model.request.UserUpdateRequest
import com.quid.kopring.user.model.response.UserResponse
import com.quid.kopring.user.User
import com.quid.kopring.user.model.response.UserLoanListResponse
import com.quid.kopring.user.repository.UserJpaRepository
import com.quid.kopring.util.fail
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(private val userRepository: UserJpaRepository) {

    @Transactional
    fun saveUser(request: UserCreateRequest) {
        User.of(request).also { userRepository.save(it) }
    }

    @Transactional(readOnly = true)
    fun getUsers(): List<UserResponse> {
        return userRepository.findAll()
            .map { UserResponse(it) }
    }

    @Transactional
    fun updateUserName(request: UserUpdateRequest) {
        val user = userRepository.findByIdOrNull(request.id) ?: fail("User not found")
        user.updateName(request.name)
    }

    @Transactional
    fun deleteUser(name: String) {
        val user =
            userRepository.findByName(name) ?: fail("User not found")
        userRepository.delete(user)
    }

    @Transactional(readOnly = true)
    fun getUserLoanList(name: String): UserLoanListResponse {
        val userLoanHistories = userRepository.findByName(name) ?.userLoanHistories ?: listOf()
        return UserLoanListResponse(name, userLoanHistories.toList())
    }
}