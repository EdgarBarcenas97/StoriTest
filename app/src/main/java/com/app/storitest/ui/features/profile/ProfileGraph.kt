package com.app.storitest.ui.features.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import app.app.temis.features.profile.delete.DeleteAccountScreen
import com.app.storitest.ui.composables.form.personalData.PersonalData
import com.app.storitest.ui.features.home.BottomNavRoutes
import kotlinx.serialization.Serializable

@Serializable
data object DeleteAccountScreenRoute

fun NavGraphBuilder.profileGraph(
    rootController: NavHostController
) {
    navigation<BottomNavRoutes.ProfileGraph>(
        startDestination = BottomNavRoutes.ProfileScreenRoute
    ) {
        composable<BottomNavRoutes.ProfileScreenRoute> {
            ProfileScreen(
                personalData = PersonalData(
                    firstName = "John",
                    lastName = "Doe",
                    email = "john.doe@gmail.com",
                    phoneNumber = "+48 123 456 789",
                ),
                onBackClick = { },
                onLogoutClick = { },
                onDeleteAccountClick = {
                    rootController.navigate(DeleteAccountScreenRoute)
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
