package com.school1c.kotlinschool.entities

import com.school1c.kotlinschool.repositories.SchoolClassRepository
import kotlinx.coroutines.flow.Flow
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(value = "schools")
data class School(@Id val id: Int? = null, val name:String) {
    suspend fun getSchoolClasses(schoolClassRepository: SchoolClassRepository):Flow<SchoolClass>{
        return schoolClassRepository.findAllBySchool(schoolId = id)
    }
}