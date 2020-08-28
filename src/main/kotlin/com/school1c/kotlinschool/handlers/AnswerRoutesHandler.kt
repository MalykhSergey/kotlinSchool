package com.school1c.kotlinschool.handlers

import com.school1c.kotlinschool.entities.User
import com.school1c.kotlinschool.entities.UserType
import com.school1c.kotlinschool.getAnswerBodyFromParameters
import com.school1c.kotlinschool.services.AnswerService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.answerRoutesHandler(answerService: AnswerService){
    post ("/addAnswer/{taskId}"){
        val taskId = call.parameters["taskId"]
        val user = call.authentication.principal<User>()
        val answerBody = getAnswerBodyFromParameters(call.request.queryParameters)
        if (user?.id != null && user.userType == UserType.Student.ordinal && taskId !=null && answerBody!=null){
            call.respond(answerService.createAnswer(taskId = taskId, answerBody = answerBody, student = user.id!!))
        } else call.respond(HttpStatusCode.BadRequest)
    }
}