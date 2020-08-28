package com.school1c.kotlinschool.entities

import com.school1c.kotlinschool.repositories.SchoolRepository
import com.school1c.kotlinschool.repositories.UserRepository
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(value = "school_classes")
data class SchoolClass(@Id var id:Int? = null, val name:String, private val school: Int) {
    suspend fun getSchool(schoolRepository: SchoolRepository): School? {
        return schoolRepository.findById(school)
    }
}