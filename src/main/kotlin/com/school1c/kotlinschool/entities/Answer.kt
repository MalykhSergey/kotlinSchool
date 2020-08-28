package com.school1c.kotlinschool.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("answers")
class Answer (@Id var id:Long? = null, @Column("student_id") val student:Long, val body:String, @Column("task_id") val task: Long){
}