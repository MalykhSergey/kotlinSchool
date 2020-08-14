package com.school1c.kotlinschool

import io.ktor.auth.UserIdPrincipal
import org.springframework.data.relational.core.mapping.Table

@Table(value = "users")
data class User (
        val id:Long? = null,
        val userName: String,
        val password:String,
        val userType:UserType): UserIdPrincipal(userName) {
}