package com.school1c.kotlinschool.services

import com.school1c.kotlinschool.entities.School
import com.school1c.kotlinschool.entities.SchoolClass
import com.school1c.kotlinschool.entities.User
import com.school1c.kotlinschool.repositories.SchoolRepository
import com.school1c.kotlinschool.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SchoolServiceImpl_Default (@Autowired val schoolRepository: SchoolRepository): SchoolService {
    override suspend fun addSchool(school: School) {
        schoolRepository.save(school)
    }

    override suspend fun addSchoolClassForSchool(school: School, schoolClass: SchoolClass) {
        TODO("Not yet implemented")
    }

    override suspend fun addUserForSchool(user: User, userRepository: UserRepository) {
        TODO("Not yet implemented")
    }
}