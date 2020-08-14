package com.school1c.kotlinschool

import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.basic
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinSchoolApplication(@Autowired userRepos: UserRepository){
	val userRepository: UserRepository = userRepos
	val embeddedServer = embeddedServer(factory = Netty, host = "127.0.0.1", port = 80){
		install(ContentNegotiation){
			jackson{}
		}
		install(Authentication){
			basic {
				validate {  }
			}
		}
		routing {
			get {
				call.respond(userRepository.findAll().toList())
			}
		}
	}
	init{
		embeddedServer.start()
	}
}
fun main(args: Array<String>) {
	runApplication<KotlinSchoolApplication>(*args)
}
