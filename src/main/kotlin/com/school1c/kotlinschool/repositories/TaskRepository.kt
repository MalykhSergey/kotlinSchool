package com.school1c.kotlinschool.repositories

import com.school1c.kotlinschool.entities.Task
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository:CoroutineCrudRepository<Task, Long>{
}