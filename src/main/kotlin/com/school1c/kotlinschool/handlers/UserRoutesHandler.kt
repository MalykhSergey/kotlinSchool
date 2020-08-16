package com.school1c.kotlinschool.handlers

import com.school1c.kotlinschool.entities.UserToken
import com.school1c.kotlinschool.entities.UserType
import com.school1c.kotlinschool.getSchoolNameFromParameters
import com.school1c.kotlinschool.getUserNameParametersFromParameters
import com.school1c.kotlinschool.getUserTypeFromParameters
import io.ktor.application.call
import io.ktor.auth.authentication
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post

fun Route.adduser(){
    post("/createUser") {
        val userToken = call.authentication.principal<UserToken>()
        if (userToken!=null){
            val queryParameters = call.request.queryParameters
            val schoolName = getSchoolNameFromParameters(queryParameters)
            val userName = getUserNameParametersFromParameters(queryParameters)
            val userType = getUserTypeFromParameters(queryParameters)
            when(userToken.userType){
                UserType.Admin -> {

                }
                UserType.Operator -> TODO()
            }
        } else {
            call.respond(HttpStatusCode.Unauthorized)
            return@post
        }
    }
}
fun createUser(userName:String, userType:String, schoolName:String){
    when(userType){
        "Operator"-> {
            TODO()
        }
        "Teacher"-> {
            TODO()
        }
        "Student"-> {
            TODO()
        }
    }
}