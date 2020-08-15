package com.school1c.kotlinschool.repositories

import com.school1c.kotlinschool.entities.SchoolClass
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface SchoolClassRepository:CoroutineCrudRepository<SchoolClass,Long> {
    suspend fun findAllBySchool(schoolId:Long?):Flow<SchoolClass>
}
