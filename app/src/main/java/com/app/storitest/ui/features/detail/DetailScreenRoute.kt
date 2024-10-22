package com.app.storitest.ui.features.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.serialization.Serializable

@Serializable
data class DetailScreenRoute(val id: String)

@Composable
fun DetailScreen(
    id: String,
    onBackListener: () -> Unit,
    transactionDetailViewModel: TransactionDetailViewModel = hiltViewModel()
) {
    val transactionDetailUiModelState by transactionDetailViewModel.transactionDetailUiModelState.collectAsState()

    LaunchedEffect(Unit) {
        transactionDetailViewModel.getTransactionDetail(id)
    }

    transactionDetailUiModelState?.let {
        TransactionDetail(
            transactionDetailUiModelState = it,
            onBackListener = onBackListener
        )
    }
}
