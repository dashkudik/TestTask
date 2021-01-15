package com.example.testtask.data_layer.remote_api_service

import com.example.testtask.data_layer.pojo.Response
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users?page=2/")
    fun getResponse(): Call<Response>
}