package com.app.storitest.ui.features.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.serialization.Serializable


@Serializable
data class DetailScreenRoute(val id: String)

@Composable
fun DetailScreen(
    id: String,
    transactionDetailViewModel: TransactionDetailViewModel = hiltViewModel(),
) {
    transactionDetailViewModel.getTransactionDetail(id)

    val transactionDetailUiModelState by transactionDetailViewModel.transactionDetailUiModelState.collectAsState()

    transactionDetailUiModelState?.let {
        TransactionDetail(
            transactionDetailUiModelState = it
        )
    }

}
