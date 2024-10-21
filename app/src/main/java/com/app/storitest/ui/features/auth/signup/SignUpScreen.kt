package com.app.storitest.ui.features.auth.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SignUpScreen(
    onBackClick: () -> Unit,
    onGoToHomeListener: () -> Unit,
    singUpViewModel: SingUpViewModel = hiltViewModel()
) {
    val uiState = singUpViewModel.signUpUiModelState.collectAsState()
    SignUpScaffold(
        onBackClick = onBackClick,
        onRegisterClick = {
            singUpViewModel.saveUserRegisterUi(it)
        }
    )
}
