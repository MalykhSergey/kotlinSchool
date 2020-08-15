package com.school1c.kotlinschool

import com.school1c.kotlinschool.entities.Operator
import com.school1c.kotlinschool.entities.UserType
import com.school1c.kotlinschool.handlers.addSchool
import com.school1c.kotlinschool.handlers.addSchoolClass
import com.school1c.kotlinschool.repositories.SchoolClassRepository
import com.school1c.kotlinschool.repositories.SchoolRepository
import com.school1c.kotlinschool.repositories.UserRepository
import com.school1c.kotlinschool.repositories.UserTokenRepository
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.UserPasswordCredential
import io.ktor.auth.authenticate
import io.ktor.auth.basic
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinSchoolApplication(@Autowired userTokenRepos: UserTokenRepository, @Autowired schoolRepository: SchoolRepository,
                              @Autowired schoolClassRepository: SchoolClassRepository, @Autowired operatorRepository: UserRepository<Operator>) {
    val userTokenRepository: UserTokenRepository = userTokenRepos
    private final val embeddedServer = embeddedServer(factory = Netty, host = "127.0.0.1", port = 80) {
        configuration(this, this@KotlinSchoolApplication)
        routing {
            authenticate("AdminUser") {
                addSchool(schoolRepository)
            }
            authenticate {
                addSchoolClass(schoolClassRepository = schoolClassRepository, schoolRepository = schoolRepository, userRepository = operatorRepository)
            }
        }
    }
    suspend fun findUserToken(it: UserPasswordCredential) =
            userTokenRepository.findUserTokenByUserNameAndPassword(it.name, it.password)

    init {
        embeddedServer.start()
    }
}

fun main(args: Array<String>) {
    runApplication<KotlinSchoolApplication>(*args)
}
