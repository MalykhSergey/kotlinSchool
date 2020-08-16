package com.school1c.kotlinschool

import com.school1c.kotlinschool.entities.School
import com.school1c.kotlinschool.entities.SchoolClass
import com.school1c.kotlinschool.entities.User
import com.school1c.kotlinschool.repositories.UserRepository

interface SchoolService {
    suspend fun addSchool(school: School)
    suspend fun addSchoolClassForSchool(school: School, schoolClass: SchoolClass)
    suspend fun addUserForSchool(user: User, userRepository: UserRepository)
}