package com.app.storitest.ui.features.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.storitest.ui.composables.BottomNavigationBar
import com.app.storitest.ui.features.detail.DetailScreen
import com.app.storitest.ui.features.detail.DetailScreenRoute
import com.app.storitest.ui.features.home.data.TransactionDetailUi
import com.app.storitest.ui.features.profile.profileGraph
import kotlinx.serialization.json.Json

@Composable
fun HomeScreen(
    rootController: NavHostController
) {
    val navigationBarController = rememberNavController()

    val showBottomBar = navigationBarController
        .currentBackStackEntryAsState().value?.destination?.route in BottomNavigation.entries.map { it.route::class.qualifiedName }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(navHostController = navigationBarController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navigationBarController,
            startDestination = BottomNavRoutes.HomeListGraph,
            modifier = Modifier.padding(innerPadding)
        ) {
            homeListGraph(rootController = rootController)
            composable<DetailScreenRoute> { backStackEntry ->
                val transactionDetailUiJson = backStackEntry.arguments?.getString("transactionDetailUi")
                val transactionDetailUi = Json.decodeFromString<TransactionDetailUi>(transactionDetailUiJson!!)
                DetailScreen(transactionDetailUi)
            }
            profileGraph(rootController = navigationBarController)
        }
    }
}
