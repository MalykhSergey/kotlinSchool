package com.school1c.kotlinschool.entities

import io.ktor.auth.Principal
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(value = "users")
class User(
        @Id  var id: Long? = null,
        @Column(value = "user_name")
        val userName: String,
        @Column(value = "nick_name")
        val nickName: String,
        @Column(value = "password")
        val password: String,
        val school:Long?,
        @Column(value = "user_type")
        val userType: Int):Principal {
}