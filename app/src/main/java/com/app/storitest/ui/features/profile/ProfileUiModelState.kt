package com.app.storitest.ui.features.profile

import com.app.storitest.ui.features.home.data.UserUi

sealed class ProfileUiModelState {
    data object Loading : ProfileUiModelState()
    data class Success(val userUi: UserUi) : ProfileUiModelState()
    data class Error(val error: Throwable) : ProfileUiModelState()
}
