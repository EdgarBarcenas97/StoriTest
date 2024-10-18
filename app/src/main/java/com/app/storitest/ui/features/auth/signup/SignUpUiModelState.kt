package com.app.storitest.ui.features.auth.signup

sealed class SignUpUiModelState {
    data object Loading : SignUpUiModelState()
    data object Success : SignUpUiModelState()
    data class Error(val error: Throwable?) : SignUpUiModelState()
}
