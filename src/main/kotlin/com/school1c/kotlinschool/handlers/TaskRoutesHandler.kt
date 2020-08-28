package com.school1c.kotlinschool.handlers

import com.school1c.kotlinschool.services.TaskService
import com.school1c.kotlinschool.entities.User
import com.school1c.kotlinschool.entities.UserType
import com.school1c.kotlinschool.getSchoolClassNameFromParameters
import com.school1c.kotlinschool.getTaskBodyFromParameters
import com.school1c.kotlinschool.getTaskTitleFromParameters
import com.school1c.kotlinschool.repositories.SchoolClassRepository
import io.ktor.application.call
import io.ktor.auth.authentication
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post

fun Route.taskRoutesHandler(taskService: TaskService, schoolClassRepository: SchoolClassRepository){
    post ("/addTask"){
        val teacher = call.authentication.principal<User>()
        val queryParameters = call.request.queryParameters
        val schoolClassName = getSchoolClassNameFromParameters(queryParameters)
        val title = getTaskTitleFromParameters(queryParameters)
        val body = getTaskBodyFromParameters(queryParameters)
        if (teacher != null && title != null && body !=null && schoolClassName!=null) {
            val schoolClass = schoolClassRepository.findSchoolClassIdByName(schoolClassName)
            if (teacher.userType == UserType.Teacher.ordinal && schoolClass != null) {
                call.respond(taskService.addTask(title = title, body = body, schoolClass = schoolClass, teacher = teacher.id!!))
            }
        }
        call.respond(HttpStatusCode.BadRequest)
    }
}