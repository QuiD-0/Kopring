package com.quid.kopring.user.model.response

import com.quid.kopring.user.User

class UserResponse(
    val id: Long,
    val name: String,
    val age: Int?
) {
    constructor(user: User) : this(
        id = user.id!!,
        name = user.name,
        age = user.age
    )
}