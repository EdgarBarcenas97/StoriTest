package com.app.storitest.ui.features.auth.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.app.storitest.R
import com.app.storitest.ui.composables.form.login.LoginForm
import com.app.storitest.ui.composables.form.login.rememberLoginFormState
import com.app.storitest.ui.theme.Space16
import com.app.storitest.ui.theme.Space24
import com.app.storitest.ui.theme.Space48
import com.app.storitest.ui.theme.Space64
import com.app.storitest.ui.theme.Space8

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScaffold(
    onBackClick: () -> Unit,
    onLoginClick: (email: String, password: String) -> Unit,
    onRegisterClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val formState = rememberLoginFormState()

            Text(
                text = stringResource(R.string.login),
                style = MaterialTheme.typography.displayMedium
            )
            Spacer(modifier = Modifier.height(Space64))
            LoginForm(
                state = formState,
                modifier = Modifier.padding(horizontal = Space24)
            )
            TextButton(
                onClick = onForgotPasswordClick,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = Space16, top = Space8)
            ) {
                Text(text = stringResource(R.string.forgot_password))
            }
            Spacer(modifier = Modifier.height(Space48))
            Button(
                onClick = {
                    onLoginClick(formState.email.value, formState.password.value)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Space24),
                enabled = formState.isValid,
            ) {
                Text(text = stringResource(R.string.login))
            }
            Spacer(modifier = Modifier.height(Space8))
            OutlinedButton(
                onClick = onRegisterClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Space24)
            ) {
                Text(text = stringResource(R.string.register))
            }
        }
    }
}
