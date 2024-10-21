package com.app.storitest.ui.features.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import app.app.temis.features.profile.delete.DeleteAccountScreen
import com.app.storitest.ui.features.home.bottomNav.BottomNavRoutes
import kotlinx.serialization.Serializable

@Serializable
data object DeleteAccountScreenRoute

fun NavGraphBuilder.profileGraph(
    navigationBarController: NavHostController
) {
    navigation<BottomNavRoutes.ProfileGraph>(
        startDestination = BottomNavRoutes.ProfileScreenRoute
    ) {
        composable<BottomNavRoutes.ProfileScreenRoute> {
            ProfileScreen(
                onBackClick = { },
                onLogoutClick = { },
                onDeleteAccountClick = {
                    navigationBarController.navigate(DeleteAccountScreenRoute)
                },
                onPersonalDataClick = {

                }
            )
        }

        composable<DeleteAccountScreenRoute> {
            DeleteAccountScreen(
                onCloseClick = { },
                onStartDeletingClick = { }
            )
        }
    }
}
