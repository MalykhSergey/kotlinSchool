package com.school1c.kotlinschool.repositories

import com.school1c.kotlinschool.entities.SchoolClass
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SchoolClassRepository:CoroutineCrudRepository<SchoolClass,Int> {
    suspend fun findAllBySchool(schoolId:Int?):Flow<SchoolClass>
    @Query("select id from school_classes where name = $1 and school = $2")
    suspend fun findIdByNameAndSchool(schoolClass:String?, school:Int?):Int?
    @Query("select id from school_classes where name = $1")
    suspend fun findSchoolClassIdByName(name:String):Int?
}
