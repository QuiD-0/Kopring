package com.quid.kopring.user.controller

import com.quid.kopring.user.model.request.UserCreateRequest
import com.quid.kopring.user.model.request.UserUpdateRequest
import com.quid.kopring.user.model.response.UserLoanListResponse
import com.quid.kopring.user.model.response.UserResponse
import com.quid.kopring.user.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController (
    private val userService: UserService
){
    @PostMapping
    fun saveUser(@RequestBody request: UserCreateRequest) {
        userService.saveUser(request)
    }

    @GetMapping
    fun getUsers(): List<UserResponse> = userService.getUsers()

    @PutMapping
    fun updateUser(@RequestBody request: UserUpdateRequest) {
        userService.updateUserName(request)
    }

    @DeleteMapping
    fun deleteUser(@RequestParam request: String) {
        userService.deleteUser(request)
    }

    @GetMapping("/loanList")
    fun getUserLoanList(@RequestParam name: String): UserLoanListResponse {
        return userService.getUserLoanList(name)
    }
}