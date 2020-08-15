package com.school1c.kotlinschool.entities

import org.springframework.data.relational.core.mapping.Table
@Table("operators")
class Operator(name: String, school: Long) : User(name = name, school = school) {
}