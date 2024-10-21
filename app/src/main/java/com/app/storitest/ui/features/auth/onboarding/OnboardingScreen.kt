package com.app.storitest.ui.features.auth.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun OnboardingScreen(
    onSignInClick: () -> Unit,
    onGoToHomeListener: () -> Unit,
    onSignUpClick: () -> Unit,
    onboardingViewModel: OnboardingViewModel = hiltViewModel()
) {
    val initSession by onboardingViewModel.initSession.collectAsState()

    initSession.isInitSession?.let {
        if (it) {
            onGoToHomeListener()
        } else {
            OnboardingContent(
                onSignInClick = onSignInClick,
                onSignUpClick = onSignUpClick
            )
        }
    }
}
