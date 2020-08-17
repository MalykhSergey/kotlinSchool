package com.school1c.kotlinschool.entities

import com.school1c.kotlinschool.repositories.SchoolRepository
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(value = "school_classes")
data class SchoolClass(@Id val id:Int? = null, val name:String, private val school: Int) {
//    suspend fun<T:User> getUsers(userRepository: UserRepository<T>): Flow<T> {
//        return userRepository.findAllBySchoolClassId(id!!)
//    }
    suspend fun getSchool(schoolRepository: SchoolRepository): School? {
        return schoolRepository.findById(school)
    }
}