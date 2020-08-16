package com.school1c.kotlinschool

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
    suspend fun createAndSaveUser(userName: String, schoolId: Long, password: String, userType: String): HttpStatusCode {
        return try {
            val user = User(userName = userName, password = password, school = schoolId, userType = UserType.valueOf(userType).ordinal)
            userRepository.save(user)
            HttpStatusCode.Created
        } catch (exception: DataIntegrityViolationException) {
            HttpStatusCode.Conflict
        }
    }
}