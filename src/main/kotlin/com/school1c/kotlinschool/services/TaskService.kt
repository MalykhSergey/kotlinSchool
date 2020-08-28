package com.school1c.kotlinschool.services

import com.school1c.kotlinschool.entities.Task
import com.school1c.kotlinschool.repositories.SchoolClassRepository
import com.school1c.kotlinschool.repositories.TaskRepository
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service

@Service
class TaskService(@Autowired val taskRepository: TaskRepository, @Autowired val schoolClassRepository: SchoolClassRepository) {
    suspend fun addTask(title: String, body: String, teacher: Long, schoolClass: Int): HttpStatusCode {
        return if (teacher in schoolClassRepository.findTeachersBySchoolClass(schoolClass).toList()){
            try {
                taskRepository.save(Task(title = title, body = body, teacher = teacher, schoolClass = schoolClass))
                HttpStatusCode.Created
            }
            catch (exception: DataIntegrityViolationException){
                HttpStatusCode.BadRequest
            }
        } else HttpStatusCode.BadRequest
    }
}
