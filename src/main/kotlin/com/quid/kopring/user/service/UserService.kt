package com.quid.kopring.user.service

import com.quid.kopring.dto.user.request.UserCreateRequest
import com.quid.kopring.dto.user.request.UserUpdateRequest
import com.quid.kopring.dto.user.response.UserResponse
import com.quid.kopring.user.User
import com.quid.kopring.user.repository.UserJpaRepository
import com.quid.kopring.util.fail
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(private val userRepository: UserJpaRepository) {

    @Transactional
    fun saveUser(request: UserCreateRequest) {
        User(
            name = request.name,
            age = request.age
        ).also { userRepository.save(it) }
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
}