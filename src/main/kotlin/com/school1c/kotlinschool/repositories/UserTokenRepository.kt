package com.school1c.kotlinschool.repositories

import com.school1c.kotlinschool.entities.UserToken
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserTokenRepository :CoroutineCrudRepository<UserToken, Long?>{
    suspend fun findUserTokenByUserNameAndPassword(userName:String, password:String): UserToken?
}