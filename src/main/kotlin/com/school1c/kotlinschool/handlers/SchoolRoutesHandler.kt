package com.school1c.kotlinschool.handlers

import com.school1c.kotlinschool.entities.School
import com.school1c.kotlinschool.entities.UserToken
import com.school1c.kotlinschool.repositories.SchoolRepository
import io.ktor.application.call
import io.ktor.auth.authentication
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import org.springframework.dao.DataIntegrityViolationException

fun Route.addSchool(schoolRepository: SchoolRepository) {
    post("/addSchool") {
        val user = call.authentication.principal<UserToken>()
        val name = call.request.queryParameters["SchoolName"]
        if (name != null) {
            try {
                schoolRepository.save(School(name = name))
            } catch (exception: DataIntegrityViolationException){
                call.respond(HttpStatusCode.Found)
                return@post
            }
            call.respond(HttpStatusCode.Created)
            return@post
        } else call.respond(HttpStatusCode.BadRequest)
    }
}