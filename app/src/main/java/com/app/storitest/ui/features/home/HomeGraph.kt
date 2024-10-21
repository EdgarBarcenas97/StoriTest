package com.app.storitest.ui.features.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object HomeGraph

fun NavGraphBuilder.homeGraph() {
    composable<HomeGraph> {
        HomeScreen()
    }
}
