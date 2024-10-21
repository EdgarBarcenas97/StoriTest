package com.app.storitest.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.app.storitest.ui.theme.GreenStori
import com.app.storitest.ui.theme.Space4

@Composable
fun LoadingProgressBar(
    paddingTop: Dp
) {
    LinearProgressIndicator(
        color = GreenStori,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = paddingTop)
            .height(Space4)
    )
}
