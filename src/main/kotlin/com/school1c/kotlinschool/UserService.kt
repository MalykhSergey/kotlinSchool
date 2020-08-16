package com.school1c.kotlinschool

import com.school1c.kotlinschool.entities.Operator
import com.school1c.kotlinschool.entities.UserToken
import com.school1c.kotlinschool.repositories.OperatorRepository
import com.school1c.kotlinschool.repositories.UserTokenRepository
import io.ktor.http.HttpStatusCode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired val operatorRepository: OperatorRepository, @Autowired val studentRepository: StudentRepository,
                  @Autowired val teacherRepository: TeacherRepository, @Autowired val userTokenRepository: UserTokenRepository) {
    suspend fun createOperator(userName:String, schoolName:String, password:String): HttpStatusCode {
        try {
            val operator = Operator(userName, schoolName.toLong())
            operatorRepository.save(operator)
            userTokenRepository.save(UserToken(userName = userName, password = password))
            return HttpStatusCode.Created
        }
        catch (exception:Exception){
            return HttpStatusCode.BadRequest
        }
    }
}