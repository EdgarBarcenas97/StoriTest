package com.app.storitest.ui.features.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.app.storitest.R
import com.app.storitest.ui.composables.LoadingProgressBar
import com.app.storitest.ui.features.home.data.UserUi
import com.app.storitest.ui.theme.Space16
import com.app.storitest.ui.theme.Space200
import com.app.storitest.ui.theme.Space64
import com.app.storitest.ui.theme.Space8

@Composable
fun ProfileScreen(
    onBackClick: () -> Unit,
    onPersonalDataClick: () -> Unit,
    onDeleteAccountClick: () -> Unit,
    onLogoutClick: () -> Unit,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val profileUiModelState by profileViewModel.profileUiModelState.collectAsState()
    profileUiModelState?.let {
        ProfileScaffold(
            onBackClick = onBackClick,
            onPersonalDataClick = onPersonalDataClick,
            onDeleteAccountClick = onDeleteAccountClick,
            onLogoutClick = onLogoutClick,
            profileUiModelState = it
        )
    }
}

@Composable
fun ProfileScaffold(
    onBackClick: () -> Unit,
    onPersonalDataClick: () -> Unit,
    onDeleteAccountClick: () -> Unit,
    onLogoutClick: () -> Unit,
    profileUiModelState: ProfileUiModelState
) = profileUiModelState.run {
    Scaffold(
        topBar = {
            TopBar(onBackClick)
        }
    ) { padding ->
        when (this) {
            is ProfileUiModelState.Loading -> LoadingProgressBar(paddingTop = padding.calculateTopPadding())
            is ProfileUiModelState.Success -> {
                ProfileSuccess(
                    padding = padding,
                    userUi = this.userUi,
                    onPersonalDataClick = onPersonalDataClick,
                    onDeleteAccountClick = onDeleteAccountClick,
                    onLogoutClick = onLogoutClick,
                )
            }

            is ProfileUiModelState.Error -> {}
        }

    }
}

@Composable
fun ProfileSuccess(
    padding: PaddingValues,
    userUi: UserUi,
    onPersonalDataClick: () -> Unit,
    onDeleteAccountClick: () -> Unit,
    onLogoutClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        PersonalDataSection(userUi = userUi, onClick = onPersonalDataClick)
        HorizontalDivider()
        DeleteAccountItem(onClick = onDeleteAccountClick)
        LogoutItem(onClick = onLogoutClick)
    }
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopBar(onBackClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(R.string.profile))
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
        }
    )
}

@Composable
private fun PersonalDataSection(userUi: UserUi, onClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(Space16),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Space8)
        ) {
            AsyncImage(
                model = userUi.picture,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(Space200)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = "${userUi.firstName} ${userUi.lastName}",
                style = MaterialTheme.typography.titleLarge
            )
            Text(text = userUi.email, style = MaterialTheme.typography.bodyMedium)
        }
        Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null)
    }
}

@Composable
private fun DeleteAccountItem(onClick: () -> Unit) {
    MyDataItem(
        text = stringResource(R.string.delete_account),
        onClick = onClick
    )
}

@Composable
private fun LogoutItem(onClick: () -> Unit) {
    MyDataItem(text = stringResource(R.string.logout), onClick = onClick)
}

@Composable
private fun MyDataItem(text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(Space16),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = text, style = MaterialTheme.typography.bodyLarge)
        Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null)
    }
    HorizontalDivider()
}
