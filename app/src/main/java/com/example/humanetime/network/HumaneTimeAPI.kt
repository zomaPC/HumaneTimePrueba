package com.example.humanetime.network

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface HumaneTimeAPI {
    @FormUrlEncoded
    @POST(Constants.BASE_URL)
    suspend fun login(
        @Field("email") email: String = Constants.EMAIL,
        @Field("password") password: String = Constants.PASSWORD
    ): Response<>
}

