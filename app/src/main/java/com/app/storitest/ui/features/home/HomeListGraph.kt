package com.app.storitest.ui.features.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.app.storitest.ui.features.detail.DetailScreenRoute
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun NavGraphBuilder.homeListGraph(
    rootController: NavHostController
) {
    navigation<BottomNavRoutes.HomeListGraph>(
        startDestination = BottomNavRoutes.HomeListScreenRoute
    ) {
        composable<BottomNavRoutes.HomeListScreenRoute> {
            HomeListScreen(
                onTransactionListener = {
                    val transactionDetailUiJson = Json.encodeToString(it)
                    rootController.currentBackStackEntry?.arguments?.putString("transactionDetailUi", transactionDetailUiJson)
                    rootController.navigate(DetailScreenRoute)
                }
            )
        }
    }
}
