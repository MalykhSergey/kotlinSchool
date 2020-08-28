package com.school1c.kotlinschool.services

import com.school1c.kotlinschool.entities.User
import com.school1c.kotlinschool.entities.UserType
import com.school1c.kotlinschool.repositories.SchoolRepository
import com.school1c.kotlinschool.repositories.UserRepository
import io.ktor.http.HttpStatusCode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired val userRepository: UserRepository) {
    suspend fun createAndSaveUser(userName: String, nickName:String, schoolId: Int, schoolClass:Int?, password: String, userType: String): HttpStatusCode {
        return try {
            val user = User(userName = userName, nickName = nickName,password = password, school = schoolId, schoolClass = schoolClass, userType = UserType.valueOf(userType).ordinal)
            userRepository.save(user)
            HttpStatusCode.Created
        } catch (exception: DataIntegrityViolationException) {
            HttpStatusCode.Conflict
        }
    }
}