package com.app.storitest.ui.features.auth.onboarding

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
    val initSessionState by onboardingViewModel.initSession.collectAsState()

    when {
        initSessionState.isLoading -> {
            Text(text = "Cargando...")
        }
        initSessionState.isInitSession -> {
            LaunchedEffect(Unit) {
                onGoToHomeListener()
            }
        }
        else -> {
            OnboardingContent(
                onSignInClick = onSignInClick,
                onSignUpClick = onSignUpClick
            )
        }
    }

}
