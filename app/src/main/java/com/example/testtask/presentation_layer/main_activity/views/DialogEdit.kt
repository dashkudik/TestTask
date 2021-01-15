package com.example.testtask.presentation_layer.main_activity.views

import com.example.testtask.data_layer.pojo.User

interface DialogEdit: ButtonEdit, ButtonRemove {
    fun showDialog(user: User, position: Int)
}