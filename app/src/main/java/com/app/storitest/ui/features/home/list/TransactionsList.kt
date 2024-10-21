package com.app.storitest.ui.features.home.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.storitest.ui.features.home.data.TransactionUi
import com.app.storitest.ui.theme.Space16
import com.app.storitest.ui.theme.Space4
import com.app.storitest.ui.theme.Space600
import com.app.storitest.ui.theme.WhiteStori

@Composable
fun TransactionsList(
    transactionUiList: List<TransactionUi>,
    onTransactionListener: (TransactionUi) -> Unit,
    modifier: Modifier = Modifier
) {
    if (transactionUiList.isEmpty()) {
        Text(text = "No transactions found", modifier = modifier.padding(top = Space600))
    } else {
        Column(modifier = modifier) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = Space16),
                modifier = Modifier
                    .background(WhiteStori)
                    .fillMaxWidth()
            ) {
                itemsIndexed(transactionUiList) { _, it ->
                    TransactionCard(
                        transactionUi = it,
                        onTransactionListener = { onTransactionListener(it) },
                        modifier = Modifier.padding(vertical = Space4)
                    )
                }
            }
        }
    }
}
