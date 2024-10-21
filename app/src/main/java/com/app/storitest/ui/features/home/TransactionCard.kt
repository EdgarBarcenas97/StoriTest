package com.app.storitest.ui.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import coil.compose.AsyncImage
import com.app.storitest.ui.features.home.data.TransactionUi
import com.app.storitest.ui.theme.BlackStori
import com.app.storitest.ui.theme.GreenStori
import com.app.storitest.ui.theme.Space16
import com.app.storitest.ui.theme.Space32
import com.app.storitest.ui.theme.Space8
import com.app.storitest.ui.theme.WhiteStori

@Composable
fun TransactionCard(
    transactionUi: TransactionUi,
    onTransactionListener: (TransactionUi) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            onClick = { onTransactionListener(transactionUi) },
            modifier = modifier
    ) {
        Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Space16),
                modifier = Modifier.padding(Space8)
        ) {
            //TransactionCardImage(transactionUi.imageUrl)
            TransactionCardDetails(transactionUi)
        }
    }
}

@Composable
private fun TransactionCardImage(
        imageUrl: String,
        modifier: Modifier = Modifier
) {
    Box(
            modifier = Modifier.background(color = GreenStori, shape = RoundedCornerShape(Space8))
    ) {
        AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = modifier.size(Space32)
        )
    }
}

@Composable
private fun RowScope.TransactionCardDetails(
    transactionUi: TransactionUi,
    modifier: Modifier = Modifier
) {
    Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.weight(weight = CardDetailsWeight, fill = true)
    ) {
        Column(
                horizontalAlignment = Alignment.Start
        ) {
            Text(
                    text = transactionUi.name,
                    style = MaterialTheme.typography.titleMedium,
            )
            Text(
                    text = transactionUi.amount,
                    color = WhiteStori
            )
        }
        Column(
                horizontalAlignment = Alignment.End
        ) {
            Text(
                    text = transactionUi.amount,
                    color = BlackStori,
                    style = MaterialTheme.typography.titleMedium,
            )
            Text(
                    text = transactionUi.date,
                    color = WhiteStori
            )
        }
    }
}

private const val CardDetailsWeight = 1f
