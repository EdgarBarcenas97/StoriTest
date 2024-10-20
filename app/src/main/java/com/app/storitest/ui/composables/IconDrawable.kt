package com.app.storitest.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun IconDrawable(
    drawableId: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(drawableId),
        contentDescription = null,
        modifier = modifier
    )
}
