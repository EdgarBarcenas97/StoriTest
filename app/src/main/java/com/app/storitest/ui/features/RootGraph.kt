package com.app.storitest.ui.features

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.app.storitest.ui.features.auth.AuthGraph
import com.app.storitest.ui.features.auth.authGraph
import com.app.storitest.ui.features.home.homeGraph

@Composable
fun RootGraph() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val rootController = rememberNavController()
        NavHost(
            navController = rootController,
            startDestination = AuthGraph
        ) {
            authGraph(rootController)
            homeGraph()
        }
    }
}
