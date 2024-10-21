package com.app.storitest.ui.features.auth.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.app.storitest.R
import com.app.storitest.core.extensions.addBullets
import com.app.storitest.ui.theme.BlackStori
import com.app.storitest.ui.theme.Space16
import com.app.storitest.ui.theme.Space200
import com.app.storitest.ui.theme.Space24
import com.app.storitest.ui.theme.Space32
import com.app.storitest.ui.theme.Space8

@Composable
fun WelcomeContent(
    onButtonClick: () -> Unit
) {
    val bullets = listOf(
        stringResource(R.string.benefit1),
        stringResource(R.string.benefit2),
        stringResource(R.string.benefit3)
    ).addBullets()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(Space24)
    ) {
        Image(
            painter = painterResource(R.drawable.stori_logo),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Space16, bottom = Space32)
        )
        Text(
            text = stringResource(R.string.welcome),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Space16)
        )
        Text(
            text = bullets,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Space200)
        )
        Button(
            onClick = onButtonClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.create_account),
                color = BlackStori,
                modifier = Modifier.padding(vertical = Space8)
            )
        }
    }
}