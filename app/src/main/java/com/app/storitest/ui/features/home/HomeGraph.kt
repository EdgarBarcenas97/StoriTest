package com.app.storitest.ui.features.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.app.storitest.core.createNavType
import com.app.storitest.ui.features.detail.DetailScreen
import com.app.storitest.ui.features.home.data.TransactionUi
import kotlin.reflect.typeOf

fun NavGraphBuilder.homeGraph(
    rootController: NavHostController
) {
    composable<HomeGraph> {
        HomeScreen(rootController = rootController)
    }
    composable<DetailScreenRoute>(
        typeMap = mapOf(typeOf<TransactionUi>() to createNavType<TransactionUi>())
    ) { backStackEntry ->
        val transactionUi: DetailScreenRoute = backStackEntry.toRoute()
        DetailScreen(transactionUi.transactionUi)
    }
}
