package com.school1c.kotlinschool

import com.school1c.kotlinschool.entities.UserType
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.basic
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson

fun configuration(application: Application, kotlinSchoolApplication: KotlinSchoolApplication) {
    application.install(ContentNegotiation) {
        jackson {}
    }
    application.install(Authentication) {
        basic {
            validate {
                kotlinSchoolApplication.findUserToken(it)
            }
        }
        basic("AdminUser") {
            validate {
                val userToken = kotlinSchoolApplication.findUserToken(it)
                if (userToken != null) {
                    if (userToken.userType == UserType.Admin.ordinal)
                        return@validate userToken
                }
                return@validate null
            }
        }
        basic ("AdminOrOperator"){
            validate {
                val userToken = kotlinSchoolApplication.findUserToken(it)
                if (userToken != null) {
                    if (userToken.userType == UserType.Admin.ordinal || userToken.userType == UserType.Operator.ordinal) return@validate userToken
                    else null
                } else null
            }
        }
    }
}