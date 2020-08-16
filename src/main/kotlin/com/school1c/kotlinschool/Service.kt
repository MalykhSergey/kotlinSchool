package com.school1c.kotlinschool

import io.ktor.http.Parameters

fun getSchoolNameFromParameters(parameters:Parameters): String? {
    return parameters["SchoolName"]
}
fun getUserNameParametersFromParameters(parameters: Parameters): String? {
    return parameters["UserName"]
}
fun getSchoolClassNameFromParameters(parameters: Parameters): String? {
    return parameters["SchoolClass"]
}
fun getUserTypeFromParameters(parameters: Parameters): String? {
    return parameters["UserType"]
}
fun getPasswordFromParameter(parameters: Parameters): String? {
    return parameters["Password"]
}