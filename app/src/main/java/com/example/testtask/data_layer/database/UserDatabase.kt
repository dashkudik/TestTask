package com.example.testtask.data_layer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testtask.data_layer.pojo.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun dao(): UserDao
}