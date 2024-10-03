package com.example.humanetime.network

import android.util.Log
import com.example.humanetime.network.Constants.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object Client {
    private val TAG = this::class.java.simpleName

    private val httpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .build()

            Log.i(TAG, "URL: ${request.url}")
            Log.i(TAG, "Headers: ${request.headers}")


            chain.proceed(request)
        }.build()

    val humaneTimeAPI: HumaneTimeAPI = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        )
        .build()
        .create(HumaneTimeAPI::class.java)
}