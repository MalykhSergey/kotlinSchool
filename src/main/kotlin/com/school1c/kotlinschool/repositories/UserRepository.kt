package com.school1c.kotlinschool.repositories

import com.school1c.kotlinschool.entities.User
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface UserRepository<T:User>{
    suspend fun findSchoolIdBuUserTokenId(id:Long):Long?
}