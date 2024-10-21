package com.app.storitest.ui.features.auth.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SignUpScreen(
    onBackListener: () -> Unit,
    onGoToHomeListener: () -> Unit,
    singUpViewModel: SingUpViewModel = hiltViewModel()
) {
    val signUpUiModelState = singUpViewModel.signUpUiModelState.collectAsState()
    val userRegisterUiModelState = singUpViewModel.userRegisterUiModelState.collectAsState()

    LaunchedEffect(Unit) {
        signUpUiModelState.value?.let {
            if (it is SignUpUiModelState.Success) {
                onGoToHomeListener()
            }
        }
    }

    SignUpScaffold(
        onBackListener = onBackListener,
        onSaveFormDataListener = {
            singUpViewModel.saveUserRegisterUi(it)
        },
        onSavePictureListener = {
            singUpViewModel.savePictureUi(it)
        },
        onRegisterUserListener = {
            userRegisterUiModelState.value?.let { singUpViewModel.singUp(it) }
        }
    )
}
