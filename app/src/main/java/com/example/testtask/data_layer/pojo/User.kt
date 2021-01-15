package com.example.testtask.data_layer.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Users")
data class User(
        @PrimaryKey(autoGenerate = true)
        @SerializedName("id")
        var id: Int,
        @ColumnInfo(name = "mail")
        @SerializedName("email")
        var mail: String,
        @ColumnInfo(name = "name")
        @SerializedName("first_name")
        var name: String,
        @ColumnInfo(name = "surname")
        @SerializedName("last_name")
        var surname: String,
        @ColumnInfo(name = "avatar")
        @SerializedName("avatar")
        var avatar: String
)