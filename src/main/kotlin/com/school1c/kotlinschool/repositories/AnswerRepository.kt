package com.school1c.kotlinschool.repositories

import com.school1c.kotlinschool.entities.Answer
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AnswerRepository:CoroutineCrudRepository<Answer, Long> {
}