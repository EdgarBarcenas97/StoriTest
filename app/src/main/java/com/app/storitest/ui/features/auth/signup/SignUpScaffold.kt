package com.app.storitest.ui.features.auth.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.app.storitest.core.extensions.empty
import com.app.storitest.ui.composables.TopBar
import com.app.storitest.ui.features.auth.signup.models.UserRegisterUi
import kotlinx.coroutines.launch

@Composable
fun SignUpScaffold(
    onBackClick: () -> Unit,
    onRegisterClick: (userRegisterUi: UserRegisterUi) -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(onBackClick = onBackClick)
        }
    ) { padding ->
        val pagerState = rememberPagerState(pageCount = { SignUpPagerSize })
        val coroutineScope = rememberCoroutineScope()
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
                            onRegisterClick(
                                UserRegisterUi(
                                    fistName = personalDataUi.firstName,
                                    lastName = personalDataUi.lastName,
                                    email = personalDataUi.email,
                                    password = password,
                                    pictureIdentification = String.empty())
                            )
                            coroutineScope.launch { pagerState.animateScrollToPage(PictureIndex) }
                        }
                    )
                    PictureIndex -> PictureContent()
                    WelcomeIndex -> WelcomeContent()
                }
            }
        }
    }
}

private const val SignUpPagerSize = 3
private const val FormIndex = 0
private const val PictureIndex = 1
private const val WelcomeIndex = 2
