package com.example.testtask.data_layer.pojo

import com.google.gson.annotations.SerializedName

data class Response(
        @SerializedName("page")
        val page: Int,
        @SerializedName("per_page")
        val perPage: Int,
        @SerializedName("total")
        val total: Int,
        @SerializedName("total_pages")
        val totalPages: Int,
        @SerializedName("data")
        val users: List<User>,
        @SerializedName("support")
        val support: Support
)