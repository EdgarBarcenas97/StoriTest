package com.app.storitest.ui.features.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.app.storitest.core.createNavType
import com.app.storitest.ui.composables.BottomNavigationBar
import com.app.storitest.ui.features.detail.DetailScreen
import com.app.storitest.ui.features.detail.DetailScreenRoute
import com.app.storitest.ui.features.home.data.TransactionUi
import com.app.storitest.ui.features.profile.profileGraph
import kotlin.reflect.typeOf

@Composable
fun HomeScreen(
    rootController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val userUiModelState by homeViewModel.userUiModelState.collectAsState()

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
            userUiModelState?.let {
                homeListGraph(
                    rootController = rootController,
                    userUiModelState = it
                )
            }

            composable<DetailScreenRoute>(typeMap = mapOf(typeOf<TransactionUi>() to createNavType<TransactionUi>())) { backStackEntry ->
                val transactionUi: TransactionUi = backStackEntry.toRoute()
                DetailScreen(transactionUi)
            }

            profileGraph(rootController = navigationBarController)
        }
    }
}
