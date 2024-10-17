package com.app.storitest.ui.composables.form.personalData

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.app.storitest.R
import com.app.storitest.ui.composables.TextField
import com.app.storitest.ui.composables.form.email.EmailField
import com.app.storitest.ui.theme.Space16

@Composable
fun PersonalDataForm(
    state: PersonalDataFormState = rememberPersonalDataFormState(),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Space16)) {
        TextField(
            icon = Icons.Default.Person,
            label = stringResource(R.string.first_name),
            state = state.firstName,
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            icon = Icons.Default.Person,
            label = stringResource(R.string.last_name),
            state = state.lastName,
            modifier = Modifier.fillMaxWidth()
        )
        EmailField(
            state = state.email,
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            icon = Icons.Default.Phone,
            label = stringResource(R.string.phone_number),
            state = state.phoneNumber,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
