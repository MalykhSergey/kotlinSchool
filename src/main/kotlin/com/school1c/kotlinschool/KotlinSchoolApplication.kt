package com.school1c.kotlinschool

import com.school1c.kotlinschool.handlers.addSchool
import com.school1c.kotlinschool.handlers.addSchoolClass
import com.school1c.kotlinschool.handlers.addUser
import com.school1c.kotlinschool.repositories.SchoolClassRepository
import com.school1c.kotlinschool.repositories.SchoolRepository
import com.school1c.kotlinschool.repositories.UserRepository
import io.ktor.auth.UserPasswordCredential
import io.ktor.auth.authenticate
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinSchoolApplication(@Autowired userRepos: UserRepository, @Autowired schoolRepository: SchoolRepository,
                              @Autowired schoolClassRepository: SchoolClassRepository, @Autowired userRepository: UserRepository, @Autowired userService: UserService) {
    val userRepository: UserRepository = userRepos
    private final val embeddedServer = embeddedServer(factory = Netty, host = "127.0.0.1", port = 80) {
        configuration(this, this@KotlinSchoolApplication)
        routing {
            authenticate("AdminUser") {
                addSchool(schoolRepository)
            }
            authenticate ("AdminOrOperator"){
                addUser(schoolRepository = schoolRepository, userService = userService)
            }
            authenticate {
                addSchoolClass(schoolClassRepository = schoolClassRepository, schoolRepository = schoolRepository, userRepository = userRepository)
            }
        }
    }
    suspend fun findUserToken(it: UserPasswordCredential) =
            userRepository.findUserByUserNameAndPassword(it.name, it.password)

    init {
        embeddedServer.start()
    }
}

fun main(args: Array<String>) {
    runApplication<KotlinSchoolApplication>(*args)
}
