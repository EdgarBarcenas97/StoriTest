package com.app.storitest.ui.features.auth.signin

sealed class SignInUiModelState {
    data object Loading : SignInUiModelState()
    data object Success : SignInUiModelState()
    data class Error(val error: Throwable?) : SignInUiModelState()
}
