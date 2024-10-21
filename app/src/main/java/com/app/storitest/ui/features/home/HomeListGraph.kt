package com.app.storitest.ui.features.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.app.storitest.ui.features.home.data.TransactionUi

fun NavGraphBuilder.homeListGraph(
    userUiModelState: UserUiModelState,
    onTransactionListener: (TransactionUi) -> Unit,
) {
    navigation<BottomNavRoutes.HomeListGraph>(
        startDestination = BottomNavRoutes.HomeListScreenRoute
    ) {
        composable<BottomNavRoutes.HomeListScreenRoute> {
            HomeListScreen(
                onTransactionListener = onTransactionListener,
                userUiModelState = userUiModelState
            )
        }
    }
}
