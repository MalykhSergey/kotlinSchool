package com.school1c.kotlinschool.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("tasks")
class Task(@Id var id: Long? = null, val title: String, val body: String,
           @Column("school_class") val schoolClass: Int, val teacher: Long) {
}