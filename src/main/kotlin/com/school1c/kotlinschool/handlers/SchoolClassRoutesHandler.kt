package com.school1c.kotlinschool.handlers

import com.school1c.kotlinschool.entities.*
import com.school1c.kotlinschool.repositories.SchoolClassRepository
import com.school1c.kotlinschool.repositories.SchoolRepository
import com.school1c.kotlinschool.repositories.UserRepository
import io.ktor.application.call
import io.ktor.auth.authentication
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post

fun Route.addSchoolClass(schoolClassRepository: SchoolClassRepository, schoolRepository: SchoolRepository, userRepository: UserRepository<Operator>){
    post("/addSchoolClass") {
        val user= call.authentication.principal<UserToken>()
        if (user != null) {
            val queryParameters = call.request.queryParameters
            val schoolClassName = queryParameters["SchoolClassName"]
            val schoolName = queryParameters["SchoolName"]
            when(user.userType){
                UserType.Admin -> {
                    if(schoolClassName !=null) {
                        schoolClassRepository.save(SchoolClass(name = schoolClassName, school = schoolRepository.findSchoolIdByName(schoolName!!)!!))
                        call.respond(HttpStatusCode.Created)
                        return@post
                    }
                }
                UserType.Operator -> {
                    if (schoolClassName != null) {
                        schoolClassRepository.save(SchoolClass(school = userRepository.findSchoolIdBuUserTokenId(user.id!!)!!, name = schoolClassName))
                        call.respond(HttpStatusCode.Created)
                        return@post
                    }
                }
            }
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }
        call.respond(HttpStatusCode.BadRequest)
    }
}