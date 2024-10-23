package com.app.storitest.ui.features.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import com.app.storitest.ui.composables.LoadingProgressBar
import com.app.storitest.ui.theme.GreenStori
import com.app.storitest.ui.theme.Space128
import com.app.storitest.ui.theme.Space16
import com.app.storitest.ui.theme.Space4
import com.app.storitest.ui.theme.Space64
import com.app.storitest.ui.theme.Space8

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionDetail(
    transactionDetailUiModelState: TransactionDetailUiModelState,
    onBackListener: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Transaction Detail")
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable { onBackListener() },
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            )
        }
    ) { paddingValues ->
        when (transactionDetailUiModelState) {
            is TransactionDetailUiModelState.Success -> {
                TransactionDetailSuccess(
                    transactionDetailUiModelState = transactionDetailUiModelState,
                    modifier = modifier.padding(paddingValues)
                )
            }

            is TransactionDetailUiModelState.Loading -> {
                LoadingProgressBar(paddingTop = paddingValues.calculateTopPadding())
            }

            is TransactionDetailUiModelState.Error -> {

            }
        }
    }
}

@Composable
fun TransactionDetailSuccess(
    transactionDetailUiModelState: TransactionDetailUiModelState.Success,
    modifier: Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Space4),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = "https://www.storicard.com/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Fhome_hero.b09c5edf.webp&w=640&q=75",
            contentDescription = null,
            modifier = Modifier
                .size(Space128)
                .align(alignment = Alignment.CenterHorizontally)
                .background(color = GreenStori, shape = CircleShape)
        )
        Text(
            text = transactionDetailUiModelState.transactionDetailUi.name,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )
        Text(
            text = transactionDetailUiModelState.transactionDetailUi.amount,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )
        Row {
            Text(
                text = "Date",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(end = Space8)
            )
            Text(
                text = transactionDetailUiModelState.transactionDetailUi.date,
                style = MaterialTheme.typography.titleMedium
            )
        }

        Row {
            Text(
                text = "Card",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(end = Space8)
            )
            Text(
                text = transactionDetailUiModelState.transactionDetailUi.card,
                style = MaterialTheme.typography.titleMedium
            )
        }
        Row {
            Text(
                text = "Reference",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(end = Space8)
            )
            Text(
                text = transactionDetailUiModelState.transactionDetailUi.reference,
                style = MaterialTheme.typography.titleMedium
            )
        }
        Row {
            Text(
                text = "Category",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(end = Space8)
            )
            Text(
                text = transactionDetailUiModelState.transactionDetailUi.category,
                style = MaterialTheme.typography.titleMedium
            )
        }
        Spacer(modifier = Modifier.height(Space16))
        Button(
            onClick = {},
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        ) {
            Icon(
                imageVector = Icons.Filled.Warning,
                contentDescription = null
            )
            Text(text = "Report movement")
        }
        Spacer(modifier = Modifier.height(Space64))
    }
}
