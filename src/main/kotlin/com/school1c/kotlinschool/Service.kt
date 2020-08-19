package com.school1c.kotlinschool

import io.ktor.http.Parameters

fun getSchoolNameFromParameters(parameters:Parameters): String? = parameters["SchoolName"]
fun getUserNameParametersFromParameters(parameters: Parameters): String? = parameters["UserName"]
fun getSchoolClassNameFromParameters(parameters: Parameters): String? = parameters["SchoolClass"]
fun getUserTypeFromParameters(parameters: Parameters): String? = parameters["UserType"]
fun getPasswordFromParameter(parameters: Parameters): String? = parameters["Password"]
fun getNickNameFromParameters(parameters: Parameters):String? = parameters["NickName"]
fun getTaskTitleFromParameters(parameters: Parameters):String? = parameters["TaskTitle"]
fun getTaskBodyFromParameters(parameters: Parameters):String? = parameters["TaskBody"]
