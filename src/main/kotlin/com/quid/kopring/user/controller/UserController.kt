package com.quid.kopring.user.controller

import com.quid.kopring.user.model.request.UserCreateRequest
import com.quid.kopring.user.model.request.UserUpdateRequest
import com.quid.kopring.user.model.response.UserResponse
import com.quid.kopring.user.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
class UserController (
    private val userService: UserService
){
    @PostMapping("/user")
    fun saveUser(@RequestBody request: UserCreateRequest) {
        userService.saveUser(request)
    }

    @GetMapping("/user")
    fun getUsers(): List<UserResponse> = userService.getUsers()

    @PutMapping("/user")
    fun updateUser(@RequestBody request: UserUpdateRequest) {
        userService.updateUserName(request)
    }

    @DeleteMapping("/user")
    fun deleteUser(@RequestParam request: String) {
        userService.deleteUser(request)
    }
}