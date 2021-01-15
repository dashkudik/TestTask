package com.example.testtask.presentation_layer.main_activity.presenters

import com.example.testtask.data_layer.database.UserDatabaseAccessor
import com.example.testtask.presentation_layer.main_activity.views.ButtonShow

class ButtonShowPresenter(private val viewState: ButtonShow) {
    companion object {
        const val MESSAGE_NO_DATA = "Для начала нужно загрузить данные"
    }

    suspend fun load() {
        with(UserDatabaseAccessor.instance!!.getUsers()) {
            if (isNotEmpty()) {
                viewState.showList(this)
            } else {
                viewState.showMessage(MESSAGE_NO_DATA)
            }
        }
    }
}