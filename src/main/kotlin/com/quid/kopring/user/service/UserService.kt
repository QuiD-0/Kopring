package com.quid.kopring.user.service

import com.quid.kopring.dto.user.request.UserCreateRequest
import com.quid.kopring.dto.user.request.UserUpdateRequest
import com.quid.kopring.dto.user.response.UserResponse
import com.quid.kopring.user.User
import com.quid.kopring.user.repository.UserJpaRepository
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
        userRepository.findById(request.id)
            .ifPresentOrElse(
                { it.name = request.name },
                { throw IllegalArgumentException("User not found") }
            )
    }

    @Transactional
    fun deleteUser(name: String) {
        userRepository.findByName(name)
            .ifPresentOrElse(
                { userRepository.delete(it) },
                { throw IllegalArgumentException("User not found") }
            )
    }
}