package com.school1c.kotlinschool.handlers

import com.school1c.kotlinschool.*
import com.school1c.kotlinschool.entities.User
import com.school1c.kotlinschool.entities.UserType
import com.school1c.kotlinschool.repositories.SchoolClassRepository
import com.school1c.kotlinschool.repositories.SchoolRepository
import com.school1c.kotlinschool.services.UserService
import io.ktor.application.call
import io.ktor.auth.authentication
import io.ktor.http.HttpStatusCode
import io.ktor.http.Parameters
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post

fun Route.userRoutesHandler(userService: UserService, schoolRepository: SchoolRepository, schoolClassRepository: SchoolClassRepository) {
    post("/createUser") {
        val userToken = call.authentication.principal<User>()
        if (userToken != null) {
            val queryParameters = call.request.queryParameters
            call.respond(parseRequestAndAddUser(schoolRepository, schoolClassRepository, userToken, userService, queryParameters))
        } else {
            call.respond(HttpStatusCode.Unauthorized)
            return@post
        }
    }
}

private suspend fun parseRequestAndAddUser(schoolRepository: SchoolRepository, schoolClassRepository: SchoolClassRepository, userToken: User, userService: UserService, queryParameters: Parameters): HttpStatusCode {
    val schoolName = getSchoolNameFromParameters(queryParameters)
    val schoolClassName = getSchoolClassNameFromParameters(queryParameters)
    val userName = getUserNameParametersFromParameters(queryParameters)
    val nickName = getNickNameFromParameters(queryParameters)
    val userType = getUserTypeFromParameters(queryParameters)
    val password = getPasswordFromParameter(queryParameters)
    return if (userName != null && userType != null && password != null && nickName != null) {
        val schoolId: Int? = if (schoolName != null && userToken.userType == UserType.valueOf("Admin").ordinal) {
            schoolRepository.findSchoolIdByName(schoolName)
        } else userToken.school
        val schoolClassId:Int? = schoolClassRepository.findIdByNameAndSchool(schoolClassName, schoolId)
        if (schoolId != null) {
            userService.createAndSaveUser(userName = userName, nickName = nickName,password = password,
                    schoolId = schoolId, schoolClass = schoolClassId,userType = userType)
        } else HttpStatusCode.NoContent
    }else HttpStatusCode.BadRequest
}