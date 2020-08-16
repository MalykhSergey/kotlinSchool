package com.school1c.kotlinschool.repositories

import com.school1c.kotlinschool.entities.User
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository :CoroutineCrudRepository<User, Long?>{
    suspend fun findUserByUserNameAndPassword(userName:String, password:String): User?
    @Query("select school from users where id = $1")
    suspend fun findSchoolIdByUserId(id:Long):Long?
}