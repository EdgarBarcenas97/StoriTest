package com.app.storitest.ui.features.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.storitest.ui.composables.LoadingProgressBar
import com.app.storitest.ui.features.home.data.TransactionUi
import com.app.storitest.ui.features.home.data.UserUi
import com.app.storitest.ui.features.home.data.UserUiModelState
import com.app.storitest.ui.features.home.list.TransactionsList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScaffold(
    userUiModelState: UserUiModelState,
    onTransactionListener: (TransactionUi) -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Home Transactions")
                }
            )
        }
    ) { padding ->
        when {
            userUiModelState.loading -> {
                LoadingProgressBar(paddingTop = padding.calculateTopPadding())
            }

            userUiModelState.error != null -> {

            }

            userUiModelState.userUi != null -> {
                HomeSuccess(
                    padding = padding,
                    userUi = userUiModelState.userUi,
                    onTransactionListener = onTransactionListener
                )
            }
        }
    }
}

@Composable
fun HomeSuccess(
    padding: PaddingValues,
    userUi: UserUi,
    onTransactionListener: (TransactionUi) -> Unit
) {
    TransactionsList(
        transactionUiList = userUi.transactions,
        onTransactionListener = onTransactionListener,
        modifier = Modifier.padding(padding)
    )
}