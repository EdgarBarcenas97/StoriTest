package com.app.storitest.ui.composables.form.email

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.app.storitest.R

@Composable
fun EmailField(
    state: EmailFieldState = rememberEmailFieldState(),
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = state.value,
        onValueChange = state::onValueChanged,
        modifier = Modifier.fillMaxWidth().then(modifier),
        label = {
            Text(text = stringResource(R.string.email))
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Email, contentDescription = null)
        },
        isError = state.error != null,
        supportingText = state.error?.let {
            { Text(text = it, style = MaterialTheme.typography.bodySmall, color = Color.Red) }
        }
    )
}

