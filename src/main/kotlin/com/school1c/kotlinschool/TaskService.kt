package com.school1c.kotlinschool

import com.school1c.kotlinschool.entities.Task
import com.school1c.kotlinschool.repositories.TaskRepository
import io.ktor.http.HttpStatusCode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service

@Service
class TaskService(@Autowired val taskRepository: TaskRepository) {
    suspend fun addTask(title: String, body: String, teacher: Long, schoolClass: Int): HttpStatusCode {
        return try {
            taskRepository.save(Task(title = title, body = body, teacher = teacher, schoolClass = schoolClass))
            HttpStatusCode.Created
        }
        catch (exception: DataIntegrityViolationException){
            HttpStatusCode.BadRequest
        }
    }
}
