package com.example.testtask.presentation_layer.main_activity.presenters

import com.example.testtask.data_layer.database.UserDatabaseAccessor
import com.example.testtask.data_layer.remote_api_service.ApiServiceAccessor
import com.example.testtask.presentation_layer.main_activity.views.ButtonUpdate

class ButtonUpdatePresenter(private val viewState: ButtonUpdate) {
    companion object {
        const val MESSAGE_UPDATED = "Данные обновлены"
        const val MESSAGE_UNKNOWN_ERROR = "Что-то пошло не так"
    }

    suspend fun update() {
        with(ApiServiceAccessor.instance!!.getUsers()) {
            if (this != null) {
                UserDatabaseAccessor.instance!!.refresh(this)
                viewState.showMessage(MESSAGE_UPDATED)
                viewState.clearList()
            } else {
                viewState.showMessage(MESSAGE_UNKNOWN_ERROR)
            }
        }
    }
}