package com.app.storitest.ui.features.auth.signin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SignInScreen(
    onBackClick: () -> Unit,
    onGoToHomeListener: () -> Unit,
    singInViewModel: SingInViewModel = hiltViewModel()
) {
    val signInUiModelState by singInViewModel.signInUiModelState.collectAsState()

    SignInScaffold(
        signInUiModelState = signInUiModelState,
        onBackClick = onBackClick,
        onLoginClick = { email, password ->
            singInViewModel.signIn(email, password)
        },
        onGoToHomeListener = onGoToHomeListener,
        onRegisterClick = {},
        onForgotPasswordClick = {}
    )
}
