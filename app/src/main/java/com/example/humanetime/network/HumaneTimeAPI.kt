package com.example.humanetime.network

import com.example.humanetime.model.EmployResponse
import com.example.humanetime.model.EmployeeSearchRequest
import com.example.humanetime.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface HumaneTimeAPI {

    @POST(Constants.LOGIN)
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ):LoginResponse

    @POST("/HumaneTime/api/AdministracionEmpleados/BusquedaEmpleado")
    suspend fun searchEmployee(
        @Header("Authorization") token: String,
        @Body requestBody: EmployeeSearchRequest
    ): EmployResponse
}

