package com.app.storitest.ui.features.auth.signup

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.app.storitest.R
import com.app.storitest.data.exception.AuthException
import com.app.storitest.ui.composables.LaunchSnackbar
import com.app.storitest.ui.composables.LoadingProgressBar
import com.app.storitest.ui.composables.SnackbarBlue
import com.app.storitest.ui.composables.TopBar
import com.app.storitest.ui.features.auth.signup.form.FormContent
import com.app.storitest.ui.features.auth.signup.form.WelcomeContent
import com.app.storitest.ui.features.auth.signup.models.UserRegisterUi
import kotlinx.coroutines.launch

@Composable
fun SignUpScaffold(
    signUpUiModelState: SignUpUiModelState? = null,
    onBackListener: () -> Unit,
    onRegisterUserListener: (userRegisterUi: UserRegisterUi) -> Unit,
    onGoToHomeListener: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val pagerState = rememberPagerState(pageCount = { SignUpPagerSize })
    val coroutineScope = rememberCoroutineScope()
    var currentPhotoUri by remember { mutableStateOf(Uri.EMPTY) }

    Scaffold(
        topBar = {
            TopBar(onBackClick = onBackListener)
        },
        snackbarHost = { SnackbarBlue(snackbarHostState) }
    ) { padding ->

        when (signUpUiModelState) {
            is SignUpUiModelState.Loading -> LoadingProgressBar(paddingTop = padding.calculateTopPadding())
            is SignUpUiModelState.Success -> {
                LaunchedEffect(Unit) {
                    coroutineScope.launch { pagerState.animateScrollToPage(WelcomeIndex) }
                }
            }
            is SignUpUiModelState.Error -> SnackbarError(errorException = signUpUiModelState.error, snackbarHostState)
            else -> {}
        }
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false
        ) {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                when (it) {
                    FormIndex -> {
                        FormContent(
                            uriPicture = currentPhotoUri,
                            onTakePhotoListener = { currentPhotoUri = it },
                            onRegisterClick = { user, password, uri ->
                                onRegisterUserListener(
                                    UserRegisterUi(
                                        fistName = user.firstName,
                                        lastName = user.lastName,
                                        email = user.email,
                                        password = password,
                                        pictureIdentification = uri.toString()
                                    )
                                )
                            }
                        )
                    }
                    WelcomeIndex -> WelcomeContent(onGoToHomeListener = onGoToHomeListener)
                }
            }
        }
    }
}


@Composable
private fun SnackbarError(
    errorException: Throwable?,
    snackbarHostState: SnackbarHostState
) = errorException?.run {
    LaunchSnackbar(
        snackbarHostState = snackbarHostState,
        message = getMessageError(this)
    )
}

@Composable
private fun getMessageError(errorException: Throwable) = when (errorException) {
    is AuthException.SignUpException -> stringResource(id = R.string.error_sign_up)
    is AuthException.UserAlreadyExistException -> stringResource(id = R.string.error_user_already_exit)
    else -> errorException.message.orEmpty()
}

private const val SignUpPagerSize = 3
private const val FormIndex = 0
private const val WelcomeIndex = 2
