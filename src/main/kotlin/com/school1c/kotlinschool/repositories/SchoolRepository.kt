package com.school1c.kotlinschool.repositories

import com.school1c.kotlinschool.entities.School
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SchoolRepository:CoroutineCrudRepository<School, Long> {
    @Query("select id from schools where name = $1")
    suspend fun findSchoolIdByName(name:String):Long?
}