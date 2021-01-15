package com.example.testtask.presentation_layer.main_activity.presenters

import com.example.testtask.data_layer.database.UserDatabaseAccessor
import com.example.testtask.data_layer.pojo.User
import com.example.testtask.presentation_layer.main_activity.views.DialogEdit

class DialogEditPresenter(private val viewState: DialogEdit) {
    suspend fun removeUser(user: User) {
        with(UserDatabaseAccessor.instance!!) {
            remove(user)
            viewState.showList(getUsers())
        }
    }

    suspend fun update(user: User, position: Int) {
        with(UserDatabaseAccessor.instance!!) {
            update(user)
            viewState.updatedAt(position)
            viewState.showList(getUsers())
        }
    }
}