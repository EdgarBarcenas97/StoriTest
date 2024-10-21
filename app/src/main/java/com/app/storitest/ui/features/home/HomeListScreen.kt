package com.app.storitest.ui.features.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.storitest.ui.composables.LoadingProgressBar
import com.app.storitest.ui.features.home.data.TransactionUi
import com.app.storitest.ui.features.home.data.UserUi

@Composable
fun HomeListScreen(
    onTransactionListener: (TransactionUi) -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val userUiModelState by homeViewModel.userUiModelState.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.getUser()
    }

    userUiModelState?.let {
        HomeListScreenScaffold(
            userUiModelState = it,
            onTransactionListener = onTransactionListener
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeListScreenScaffold(
    userUiModelState: UserUiModelState,
    onTransactionListener: (TransactionUi) -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        when (userUiModelState) {
            is UserUiModelState.Loading -> {
                LoadingProgressBar(paddingTop = padding.calculateTopPadding())
            }

            is UserUiModelState.Success -> {
                HomeSuccess(
                    padding = padding,
                    userUi = userUiModelState.userUi,
                    onTransactionListener = onTransactionListener
                )

            }

            is UserUiModelState.Error -> {

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