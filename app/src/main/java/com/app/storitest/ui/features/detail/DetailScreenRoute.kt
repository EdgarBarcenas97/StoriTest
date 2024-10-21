package com.app.storitest.ui.features.detail

import androidx.compose.runtime.Composable
import com.app.storitest.ui.features.home.data.TransactionDetailUi
import kotlinx.serialization.Serializable

@Serializable
data object DetailScreenRoute

@Composable
fun DetailScreen(transactionDetailUi: TransactionDetailUi) {
    TransactionDetail(
        transactionDetailUi = transactionDetailUi
    )
}
