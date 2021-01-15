package com.example.testtask.presentation_layer.main_activity.views

import com.example.testtask.data_layer.pojo.User

interface RecyclerUsers {
    fun showList(users: List<User>)
    fun clearList()
    fun updatedAt(position: Int)
}