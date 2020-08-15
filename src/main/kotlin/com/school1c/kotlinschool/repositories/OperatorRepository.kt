package com.school1c.kotlinschool.repositories

import com.school1c.kotlinschool.entities.Operator
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OperatorRepository:UserRepository<Operator>, CoroutineCrudRepository<Operator, Long> {
    @Query("select school from operators where id =$1")
    override suspend fun findSchoolIdBuUserTokenId(id: Long): Long?
}