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
import androidx.compose.ui.Modifier
import com.app.storitest.ui.composables.TopBar
import com.app.storitest.ui.composables.form.personalData.PersonalDataUi

@Composable
fun SignUpScaffold(
    onBackClick: () -> Unit,
    onRegisterClick: (personalDataUi: PersonalDataUi, password: String) -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(onBackClick = onBackClick)
        }
    ) { padding ->
        val pagerState = rememberPagerState(pageCount = { SignUpPagerSize })
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
                    FormIndex -> FormContent(onRegisterClick = onRegisterClick)
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
