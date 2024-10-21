package com.app.storitest.ui.features.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.homeListGraph(
    rootController: NavHostController
) {
    navigation<BottomNavRoutes.HomeListGraph>(
        startDestination = BottomNavRoutes.HomeListScreenRoute
    ) {
        composable<BottomNavRoutes.HomeListScreenRoute> {
            HomeListScreen()
        }
    }
}