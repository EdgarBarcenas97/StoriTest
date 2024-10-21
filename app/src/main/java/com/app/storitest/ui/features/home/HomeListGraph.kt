package com.app.storitest.ui.features.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.app.storitest.core.createNavType
import com.app.storitest.ui.features.detail.DetailScreen
import com.app.storitest.ui.features.home.bottomNav.BottomNavRoutes
import com.app.storitest.ui.features.home.data.TransactionUi
import com.app.storitest.ui.features.home.data.UserUiModelState
import kotlin.reflect.typeOf

fun NavGraphBuilder.homeListGraph(
    navigationBarController: NavHostController,
    userUiModelState: UserUiModelState,
) {
    navigation<BottomNavRoutes.HomeInitGraph>(
        startDestination = BottomNavRoutes.HomeListScreenRoute
    ) {
        composable<BottomNavRoutes.HomeListScreenRoute> {
            HomeListScreen(
                onTransactionListener = {
                    navigationBarController.navigate(BottomNavRoutes.DetailScreenRoute(it))
                },
                userUiModelState = userUiModelState
            )
        }
        composable<BottomNavRoutes.DetailScreenRoute>(
            typeMap = mapOf(typeOf<TransactionUi>() to createNavType<TransactionUi>())
        ) { backStackEntry ->
            val transactionUi: BottomNavRoutes.DetailScreenRoute = backStackEntry.toRoute()
            DetailScreen(transactionUi.transactionUi)
        }
    }
}
