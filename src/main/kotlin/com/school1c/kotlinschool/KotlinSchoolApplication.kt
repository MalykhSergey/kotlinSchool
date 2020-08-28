package com.school1c.kotlinschool

import com.school1c.kotlinschool.entities.User
import com.school1c.kotlinschool.handlers.*
import com.school1c.kotlinschool.repositories.SchoolClassRepository
import com.school1c.kotlinschool.repositories.SchoolRepository
import com.school1c.kotlinschool.repositories.UserRepository
import com.school1c.kotlinschool.services.AnswerService
import com.school1c.kotlinschool.services.TaskService
import com.school1c.kotlinschool.services.UserService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinSchoolApplication(
        @Autowired userRepos: UserRepository, @Autowired schoolRepository: SchoolRepository,
        @Autowired schoolClassRepository: SchoolClassRepository, @Autowired userRepository: UserRepository,
        @Autowired userService: UserService, @Autowired taskService: TaskService,
        @Autowired answerService: AnswerService) {
    val userRepository: UserRepository = userRepos
    private final val embeddedServer = embeddedServer(factory = Netty, host = "127.0.0.1", port = 80) {
        configuration(this, this@KotlinSchoolApplication)
        install(CORS){
            method(HttpMethod.Get)
            header("Authorization")
            anyHost()
        }
        routing {
            authenticate("AdminUser") {
                schoolRoutesHandler(schoolRepository)
            }
            authenticate("AdminOrOperator") {
                userRoutesHandler(schoolRepository = schoolRepository, userService = userService, schoolClassRepository = schoolClassRepository)
            }
            authenticate {
                get ("/login"){
                    call.respond(HttpStatusCode.Accepted, call.authentication.principal<User>()!!.userType)
                }
                schoolClassHandler(schoolClassRepository = schoolClassRepository, schoolRepository = schoolRepository, userRepository = userRepository)
                taskRoutesHandler(schoolClassRepository = schoolClassRepository, taskService = taskService)
                answerRoutesHandler(answerService)
            }
        }
    }

    suspend fun findUserToken(it: UserPasswordCredential) =
            userRepository.findUserByNickNameAndPassword(it.name, it.password)

    init {
        embeddedServer.start()
    }
}

fun main(args: Array<String>) {
    runApplication<KotlinSchoolApplication>(*args)
}
