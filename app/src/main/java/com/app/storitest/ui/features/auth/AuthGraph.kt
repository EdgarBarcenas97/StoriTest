package com.app.storitest.ui.features.auth

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.app.storitest.ui.features.auth.onboarding.OnboardingScreen
import com.app.storitest.ui.features.auth.onboarding.OnboardingViewModel
import com.app.storitest.ui.features.auth.signin.SignInScreen
import com.app.storitest.ui.features.auth.signup.SignUpScreen
import com.app.storitest.ui.features.home.HomeGraph
import kotlinx.serialization.Serializable

@Serializable
data object AuthGraph

@Serializable
data object OnboardingScreenRoute

@Serializable
data object SignInScreenRoute

@Serializable
data object SignUpScreenRoute

fun NavGraphBuilder.authGraph(
    rootController: NavHostController
) {
    navigation<AuthGraph>(
        startDestination = OnboardingScreenRoute
    ) {
        composable<OnboardingScreenRoute> {
            OnboardingScreen(
                onSignInClick = { rootController.navigate(SignInScreenRoute) },
                onSignUpClick = { rootController.navigate(SignUpScreenRoute) },
                onGoToHomeListener = {
                    rootController.navigate(HomeGraph) {
                        popUpTo(rootController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable<SignInScreenRoute> {
            SignInScreen(
                onBackClick = { rootController.popBackStack() },
                onRegisterClick  = {
                    rootController.navigate(SignUpScreenRoute)
                },
                onGoToHomeListener = {
                    rootController.navigate(HomeGraph) {
                        popUpTo(rootController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable<SignUpScreenRoute> {
            SignUpScreen(
                onBackListener = {
                    rootController.popBackStack()
                },
                onGoToHomeListener = {
                    rootController.navigate(HomeGraph) {
                        popUpTo(rootController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}
