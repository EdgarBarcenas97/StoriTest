package com.app.storitest.ui.features.home

import com.app.storitest.ui.features.home.data.UserUi

sealed class UserUiModelState {
    data object Loading : UserUiModelState()
    data class Success(val userUi: UserUi) : UserUiModelState()
    data class Error(val error: Throwable) : UserUiModelState()
}
