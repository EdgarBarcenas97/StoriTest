package com.app.storitest.ui.composables

import android.annotation.SuppressLint
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import com.app.storitest.R
import com.app.storitest.ui.theme.ErrorStori
import kotlinx.coroutines.launch

@Composable
fun SnackbarBlue(snackbarHostState: SnackbarHostState) {
    SnackbarHost(hostState = snackbarHostState) {
        Snackbar(
            snackbarData = it,
            containerColor = ErrorStori,
        )
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LaunchSnackbar(
    snackbarHostState: SnackbarHostState,
    message: String = stringResource(id = R.string.error)
) {
    val scope = rememberCoroutineScope()
    scope.launch {
        snackbarHostState.showSnackbar(message = message)
    }
}
