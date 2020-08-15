package com.school1c.kotlinschool.entities

import com.school1c.kotlinschool.repositories.UserRepository
import io.ktor.auth.Principal
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(value = "users_tokens")
class UserToken(
        @Id val id: Long? = null,
        @Column(value = "user_name")
        val userName: String,
        @Column(value = "password")
        val password: String,
        @Column(value = "user_type")
        val userType: UserType,
        @Column(value = "user_id")
        var user:Long):Principal {
//    suspend fun <T:User> getUser(userRepository: UserRepository<T>): T? = userRepository.findUserByIdOfUserToken(user)
}