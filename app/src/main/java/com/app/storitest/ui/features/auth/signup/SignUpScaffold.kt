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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.app.storitest.core.extensions.empty
import com.app.storitest.ui.composables.TopBar
import com.app.storitest.ui.features.auth.signup.models.UserRegisterUi
import com.app.storitest.ui.features.auth.signup.steps.FormContent
import com.app.storitest.ui.features.auth.signup.steps.PictureContent
import com.app.storitest.ui.features.auth.signup.steps.WelcomeContent
import kotlinx.coroutines.launch

@Composable
fun SignUpScaffold(
    onBackListener: () -> Unit,
    onSaveFormDataListener: (userRegisterUi: UserRegisterUi) -> Unit,
    onSavePictureListener: (photoUri: Uri) -> Unit,
    onRegisterUserListener: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(onBackClick = onBackListener)
        }
    ) { padding ->
        val pagerState = rememberPagerState(pageCount = { SignUpPagerSize })
        val coroutineScope = rememberCoroutineScope()
        var currentPhotoUri by remember { mutableStateOf(Uri.EMPTY) }
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
                    FormIndex -> FormContent(
                        onRegisterClick = { personalDataUi, password ->
                            onSaveFormDataListener(
                                UserRegisterUi(
                                    fistName = personalDataUi.firstName,
                                    lastName = personalDataUi.lastName,
                                    email = personalDataUi.email,
                                    password = password,
                                    pictureIdentification = String.empty()
                                )
                            )
                            coroutineScope.launch { pagerState.animateScrollToPage(PictureIndex) }
                        }
                    )

                    PictureIndex -> PictureContent(
                        photoUri = currentPhotoUri,
                        onTakePhotoListener = {
                            currentPhotoUri = it
                            onSavePictureListener(it)
                        },
                        onPreviousListener = {
                            coroutineScope.launch { pagerState.animateScrollToPage(FormIndex) }
                        },
                        onSignUpListener = {
                            coroutineScope.launch { pagerState.animateScrollToPage(WelcomeIndex) }
                        }
                    )

                    WelcomeIndex -> WelcomeContent(onButtonClick = onRegisterUserListener)
                }
            }
        }
    }
}

private const val SignUpPagerSize = 3
private const val FormIndex = 0
private const val PictureIndex = 1
private const val WelcomeIndex = 2
