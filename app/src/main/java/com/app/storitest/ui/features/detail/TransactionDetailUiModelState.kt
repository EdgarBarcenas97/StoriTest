package com.app.storitest.ui.features.detail

import com.app.storitest.ui.features.home.data.TransactionDetailUi

sealed class TransactionDetailUiModelState {
    data object Loading : TransactionDetailUiModelState()
    data class Success(val transactionDetailUi: TransactionDetailUi) : TransactionDetailUiModelState()
    data class Error(val error: Throwable) : TransactionDetailUiModelState()
}
