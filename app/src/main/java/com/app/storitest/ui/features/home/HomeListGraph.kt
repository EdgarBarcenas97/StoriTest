package com.app.storitest.ui.features.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.homeListGraph(
    rootController: NavHostController,
    userUiModelState: UserUiModelState
) {
    navigation<BottomNavRoutes.HomeListGraph>(
        startDestination = BottomNavRoutes.HomeListScreenRoute
    ) {
        composable<BottomNavRoutes.HomeListScreenRoute> {
            HomeListScreen(
                onTransactionListener = {
                    rootController.navigate(BottomNavRoutes.DetailScreenRoute(it))
                },
                userUiModelState = userUiModelState
            )
        }
    }
}
