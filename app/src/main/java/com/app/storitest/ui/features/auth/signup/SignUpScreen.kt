package com.app.storitest.ui.features.auth.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SignUpScreen(
    onBackListener: () -> Unit,
    onGoToHomeListener: () -> Unit,
    singUpViewModel: SingUpViewModel = hiltViewModel()
) {
    val signUpUiModelState by singUpViewModel.signUpUiModelState.collectAsState()

    SignUpScaffold(
        signUpUiModelState = signUpUiModelState,
        onBackListener = onBackListener,
        onRegisterUserListener = {
            singUpViewModel.singUp(it)
        },
        onGoToHomeListener = onGoToHomeListener
    )
}
