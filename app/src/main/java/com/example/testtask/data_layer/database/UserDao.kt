package com.example.testtask.data_layer.database

import androidx.room.*
import com.example.testtask.data_layer.pojo.User

@Dao
interface UserDao {
    @Query("SELECT * FROM USERS")
    fun get(): List<User>

    @Delete
    fun remove(user: User)

    @Insert
    fun add(user: User)

    @Query("DELETE FROM USERS")
    fun clear()

    @Update
    fun update(user: User)
}