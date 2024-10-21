package com.app.storitest.ui.features.detail

import androidx.compose.runtime.Composable
import com.app.storitest.ui.features.home.data.TransactionUi
import kotlinx.serialization.Serializable


@Serializable
data class DetailScreenRoute(val transactionUi: TransactionUi)

@Composable
fun DetailScreen(transactionUi: TransactionUi) {
    TransactionDetail(
        transactionUi = transactionUi
    )
}
