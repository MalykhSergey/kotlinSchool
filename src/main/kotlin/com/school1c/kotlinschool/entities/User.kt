package com.school1c.kotlinschool.entities

import com.school1c.kotlinschool.repositories.SchoolRepository
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table
abstract class User (@Id val id:Long?=null, val name:String, @Column(value = "school") val school: Long){
     suspend fun getSchool(schoolRepository: SchoolRepository): School? {
        return schoolRepository.findById(school)
    }
}