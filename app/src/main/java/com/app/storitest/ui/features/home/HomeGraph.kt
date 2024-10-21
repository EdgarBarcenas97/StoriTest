package com.app.storitest.ui.features.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.homeGraph() {
    composable<HomeGraph> {
        HomeScreen()
    }

}
