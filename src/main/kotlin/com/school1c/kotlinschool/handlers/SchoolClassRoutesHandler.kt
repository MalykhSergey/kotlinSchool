package com.school1c.kotlinschool.handlers

import com.school1c.kotlinschool.entities.*
import com.school1c.kotlinschool.getSchoolClassNameFromParameters
import com.school1c.kotlinschool.getSchoolNameFromParameters
import com.school1c.kotlinschool.repositories.SchoolClassRepository
import com.school1c.kotlinschool.repositories.SchoolRepository
import com.school1c.kotlinschool.repositories.UserRepository
import io.ktor.application.call
import io.ktor.auth.authentication
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post

fun Route.schoolClassHandler(schoolClassRepository: SchoolClassRepository, schoolRepository: SchoolRepository, userRepository:UserRepository){
    post("/addSchoolClass") {
        val user= call.authentication.principal<User>()
        if (user != null) {
            val queryParameters = call.request.queryParameters
            val schoolClassName = getSchoolClassNameFromParameters(queryParameters)
            val schoolName = getSchoolNameFromParameters(queryParameters)
            when(user.userType){
                UserType.Admin.ordinal -> {
                    if(schoolClassName !=null && schoolName !=null) {
                        val school = schoolRepository.findSchoolIdByName(schoolName)
                        if (school!=null){
                            schoolClassRepository.save(SchoolClass(name = schoolClassName, school = school))
                            call.respond(HttpStatusCode.Created)
                            return@post
                        }
                    }
                }
                UserType.Operator.ordinal -> {
                    if (schoolClassName != null) {
                        schoolClassRepository.save(SchoolClass(school = userRepository.findSchoolIdByUserId(user.id!!)!!, name = schoolClassName))
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