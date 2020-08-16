package com.school1c.kotlinschool.handlers

import com.school1c.kotlinschool.*
import com.school1c.kotlinschool.entities.User
import com.school1c.kotlinschool.entities.UserType
import com.school1c.kotlinschool.repositories.SchoolRepository
import io.ktor.application.call
import io.ktor.auth.authentication
import io.ktor.http.HttpStatusCode
import io.ktor.http.Parameters
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post

fun Route.addUser(userService: UserService, schoolRepository: SchoolRepository) {
    post("/createUser") {
        val userToken = call.authentication.principal<User>()
        if (userToken != null) {
            val queryParameters = call.request.queryParameters
            call.respond(parseRequestAndAddUser(schoolRepository, userToken, userService, queryParameters))
        } else {
            call.respond(HttpStatusCode.Unauthorized)
            return@post
        }
    }
}

private suspend fun parseRequestAndAddUser(schoolRepository: SchoolRepository, userToken: User, userService: UserService, queryParameters: Parameters): HttpStatusCode {
    val schoolName = getSchoolNameFromParameters(queryParameters)
    val userName = getUserNameParametersFromParameters(queryParameters)
    val userType = getUserTypeFromParameters(queryParameters)
    val password = getPasswordFromParameter(queryParameters)
    return if (userName != null && userType != null && password != null) {
        val schoolId: Long? = if (schoolName != null && userToken.userType == UserType.valueOf("Admin").ordinal) {
            schoolRepository.findSchoolIdByName(schoolName)
        } else userToken.school
        if (schoolId != null) {
            userService.createAndSaveUser(userName = userName, password = password,
                    schoolId = schoolId, userType = userType)
        } else HttpStatusCode.NoContent
    }else HttpStatusCode.NoContent
}