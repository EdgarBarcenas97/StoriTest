package com.app.storitest.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.app.storitest.core.extensions.one
import com.app.storitest.ui.features.home.BottomNavRoutes
import com.app.storitest.ui.features.home.BottomNavigation
import com.app.storitest.ui.theme.GreenStori

@Composable
fun BottomNavigationBar(
    navHostController: NavHostController
) {

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: BottomNavRoutes.HomeListGraph::class.qualifiedName.orEmpty()

    val currentRouteTrimmed by remember(currentRoute) {
        derivedStateOf { currentRoute.substringBefore("?") }
    }

    NavigationBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        BottomNavigation.entries.forEachIndexed { _, navigationItem ->

            val isSelected by remember(currentRoute) {
                derivedStateOf { currentRouteTrimmed == navigationItem.route::class.qualifiedName }
            }

            NavigationBarItem(
                selected = isSelected,
                label = {
                    Text(
                        text = stringResource(navigationItem.title),
                        maxLines = Int.one()
                    )
                },
                icon = {
                    IconWithBadge(
                        isSelected = isSelected,
                        navigationItem= navigationItem
                    )
                },
                onClick = {
                    navHostController.navigate(navigationItem.route) {
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(selectedIconColor = GreenStori)
            )
        }
    }
}

@Composable
fun IconWithBadge(
    isSelected: Boolean,
    navigationItem: BottomNavigation,
) = navigationItem.run {
    BadgedBox(
        badge = {
            if (badgeCount != null) {
                Badge {
                    Text(text = badgeCount.toString())
                }
            } else if (hasNews) {
                Badge()
            }
        }
    ) {
        IconDrawable(
            drawableId = if (isSelected) {
                selectedIcon
            } else {
                unselectedIcon
            }
        )
    }
}
