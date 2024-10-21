package com.app.storitest.ui.features.home.bottomNav

import com.app.storitest.R
import kotlinx.serialization.Serializable

sealed class BottomNavRoutes {

    @Serializable
    data object HomeListScreenRoute : BottomNavRoutes()

    @Serializable
    data object ProfileGraph : BottomNavRoutes()

    @Serializable
    data object ProfileScreenRoute : BottomNavRoutes()
}

enum class BottomNavigation(
    val title: Int,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val hasNews: Boolean,
    val route: BottomNavRoutes,
    val badgeCount: Int? = null
) {
    CHAT(R.string.home, R.drawable.ic_chat_fill, R.drawable.ic_chat, false, BottomNavRoutes.HomeListScreenRoute),
    PROFILE(R.string.profile, R.drawable.ic_profile_fill, R.drawable.ic_profile, false, BottomNavRoutes.ProfileScreenRoute)
}