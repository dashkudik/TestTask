package com.example.testtask.data_layer.pojo

import com.google.gson.annotations.SerializedName

data class Support(
        @SerializedName("url")
        val url: String,
        @SerializedName("text")
        val text: String
)