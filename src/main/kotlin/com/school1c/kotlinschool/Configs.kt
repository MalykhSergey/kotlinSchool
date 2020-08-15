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
                    if (userToken.userType == UserType.Admin)
                        return@validate userToken
                }
                return@validate null
            }
        }
    }
}