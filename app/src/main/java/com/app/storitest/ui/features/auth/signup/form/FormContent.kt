package com.app.storitest.ui.features.auth.signup.form

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.app.storitest.R
import com.app.storitest.core.createTempPictureUri
import com.app.storitest.core.toast
import com.app.storitest.ui.composables.form.personalData.PersonalDataUi
import com.app.storitest.ui.composables.form.registration.RegistrationForm
import com.app.storitest.ui.composables.form.registration.rememberRegistrationFormState
import com.app.storitest.ui.theme.BlackStori
import com.app.storitest.ui.theme.Space16
import com.app.storitest.ui.theme.Space200
import com.app.storitest.ui.theme.Weight1

@Composable
fun FormContent(
    uriPicture: Uri,
    onRegisterClick: (personalDataUi: PersonalDataUi, password: String, photoUri: Uri) -> Unit,
    onTakePhotoListener: (Uri) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var tempPhotoUri by remember { mutableStateOf(value = Uri.EMPTY) }
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) onTakePhotoListener(tempPhotoUri)
            context.toast("Photo taken: $success")
        }
    )
    Column(
        modifier.fillMaxSize()
    ) {
        val formState = rememberRegistrationFormState()
        RegistrationForm(
            state = formState,
            modifier = Modifier.padding(Space16)
        )
        if (formState.isValid) {
            Button(
                onClick = {
                    tempPhotoUri = context.createTempPictureUri()
                    cameraLauncher.launch(tempPhotoUri)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Space16),
            ) {
                Text(
                    text = stringResource(R.string.take_photo),
                    color = BlackStori
                )
            }
            if (uriPicture.toString().isNotBlank()) {
                AsyncImage(
                    model = tempPhotoUri.toString(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(Space200)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
        Spacer(modifier = Modifier.weight(Weight1))
        Button(
            onClick = {
                onRegisterClick(
                    formState.personalData.personalDataUi,
                    formState.passwords.firstPassword.value,
                    tempPhotoUri
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(Space16),
            enabled = formState.isValid && uriPicture.toString().isNotBlank()
        ) {
            Text(
                text = stringResource(R.string.register),
                color = BlackStori)
        }
    }
}
