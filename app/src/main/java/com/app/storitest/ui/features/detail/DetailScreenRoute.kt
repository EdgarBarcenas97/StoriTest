package com.app.storitest.ui.features.detail

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

@Serializable
data object DetailScreenRoute

@Composable
fun DetailScreen() {
    DetailScaffold()
}
