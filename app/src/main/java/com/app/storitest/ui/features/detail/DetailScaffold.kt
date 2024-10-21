package com.app.storitest.ui.features.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.app.storitest.ui.features.home.data.TransactionDetailUi
import com.app.storitest.ui.theme.Space16
import com.app.storitest.ui.theme.Space4
import com.app.storitest.ui.theme.Space64

@Composable
fun DetailScaffold() {

}

@Composable
fun TransactionDetail(
    transactionDetailUi: TransactionDetailUi,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Space4),
        horizontalAlignment = Alignment.Start
    ) {
        //AsyncImage(
        //    model = transactionDetailUi.imageUrl,
        //    contentDescription = null,
        //    modifier = Modifier
        //        .size(Space128)
        //        .align(alignment = Alignment.CenterHorizontally)
        //        .background(color = Purple40, shape = CircleShape)
        //)
        Text(
            text = transactionDetailUi.name,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )
        Text(
            text = transactionDetailUi.amount,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )
        Text(
            text = "Date",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = transactionDetailUi.date,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Time",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = transactionDetailUi.date,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Category",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = transactionDetailUi.category,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(Space16))
        Button(
            onClick = { },
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

@Composable
@Preview(showBackground = true)
fun DetailScaffoldPreview() {

}
