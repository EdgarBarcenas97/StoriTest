package com.app.storitest.ui.features.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

fun NavGraphBuilder.homeGraph(
    rootController: NavHostController
) {
    composable<HomeGraph> {
        HomeScreen(rootController = rootController)
    }
}
