package com.example.testtask.data_layer.database

import android.content.Context
import androidx.room.Room
import com.example.testtask.R
import com.example.testtask.data_layer.pojo.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserDatabaseAccessor(private val context: Context) : CoroutineScope {
    override val coroutineContext = IO
    private fun requireDao() =
            Room.databaseBuilder(
                    context,
                    UserDatabase::class.java,
                    context.getString(R.string.db_name)
            ).build().dao()

    companion object {
        var instance: UserDatabaseAccessor? = null
            private set

        fun provide(context: Context) {
            instance = UserDatabaseAccessor(context)
        }
    }

    fun refresh(users: List<User>) {
        launch {
            with(requireDao()) {
                clear()
                users.forEach { add(it) }
            }
        }
    }

    suspend fun remove(user: User) =
            withContext(coroutineContext) {
                requireDao().remove(user)
            }


    suspend fun getUsers() =
            withContext(coroutineContext) {
                requireDao().get()
            }

    suspend fun update(user: User) =
            withContext(coroutineContext) {
                requireDao().update(user)
            }
}