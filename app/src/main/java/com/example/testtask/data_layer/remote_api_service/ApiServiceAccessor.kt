package com.example.testtask.data_layer.remote_api_service

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class ApiServiceAccessor : CoroutineScope {
    override val coroutineContext = IO

    companion object {
        const val BASE_URL = "https://reqres.in/api/"
        var instance: ApiServiceAccessor? = null
            private set

        fun provide() {
            instance = ApiServiceAccessor()
        }
    }

    private val retrofitService =
            Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)

    suspend fun getUsers() =
            withContext(coroutineContext) {
                try {
                    retrofitService.getResponse().execute().body()!!.users
                } catch (e: IOException) {
                    null
                }
            }
}