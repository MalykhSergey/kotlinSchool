package com.school1c.kotlinschool.services

import com.school1c.kotlinschool.entities.Answer
import com.school1c.kotlinschool.repositories.AnswerRepository
import com.school1c.kotlinschool.repositories.TaskRepository
import io.ktor.http.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service

@Service
class AnswerService (@Autowired val answerRepository: AnswerRepository, @Autowired val taskRepository: TaskRepository){
    suspend fun createAnswer(taskId:String, answerBody:String, student:Long):HttpStatusCode{
        return try {
            answerRepository.save(Answer(student = student, body = answerBody, task = taskId.toLong()))
            HttpStatusCode.Created
        }
        catch (exception: DataIntegrityViolationException){
            HttpStatusCode.BadRequest
        }
    }
}