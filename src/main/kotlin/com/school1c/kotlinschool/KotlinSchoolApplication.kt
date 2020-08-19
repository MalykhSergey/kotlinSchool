package com.school1c.kotlinschool

import com.school1c.kotlinschool.handlers.addSchoolHandler
import com.school1c.kotlinschool.handlers.addSchoolClassHandler
import com.school1c.kotlinschool.handlers.addTaskHandler
import com.school1c.kotlinschool.handlers.addUserHandler
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
                              @Autowired schoolClassRepository: SchoolClassRepository, @Autowired userRepository: UserRepository,
                              @Autowired userService: UserService, @Autowired taskService: TaskService) {
    val userRepository: UserRepository = userRepos
    private final val embeddedServer = embeddedServer(factory = Netty, host = "127.0.0.1", port = 80) {
        configuration(this, this@KotlinSchoolApplication)
        routing {
            authenticate("AdminUser") {
                addSchoolHandler(schoolRepository)
            }
            authenticate("AdminOrOperator") {
                addUserHandler(schoolRepository = schoolRepository, userService = userService, schoolClassRepository = schoolClassRepository)
            }
            authenticate {
                addSchoolClassHandler(schoolClassRepository = schoolClassRepository, schoolRepository = schoolRepository, userRepository = userRepository)
                addTaskHandler(schoolClassRepository = schoolClassRepository, taskService = taskService)
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
