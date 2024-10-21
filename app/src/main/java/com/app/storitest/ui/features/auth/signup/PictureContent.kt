package com.app.storitest.ui.features.auth.signup

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import com.app.storitest.core.createTempPictureUri
import com.app.storitest.ui.theme.Space200
import com.app.storitest.ui.theme.Space64

@Composable
fun PictureContent(
    photoUri: Uri,
    onTakePhotoListener: (Uri) -> Unit,
    onPreviousListener: () -> Unit,
    onSignUpListener: () -> Unit
) {
    val context = LocalContext.current
    var tempPhotoUri by remember { mutableStateOf(value = Uri.EMPTY) }
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success -> if (success) onTakePhotoListener(tempPhotoUri) }
    )
    Text(
        text = "Take an ID photo to finish your registration",
        style = MaterialTheme.typography.titleLarge,
    )
    Button(
        onClick = {
            tempPhotoUri = context.createTempPictureUri()
            cameraLauncher.launch(tempPhotoUri)
        },
        modifier = Modifier
            .padding(bottom = Space64)
    ) {
        Text("Take Photo")
    }
    AsyncImage(
        model = photoUri.toString().ifBlank { "https://i.pinimg.com/736x/a4/79/14/a47914817fc898b1bbb750ae18f7eea3.jpg" },
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(Space200)
    )
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Space64)
    ) {
        Button(
            onClick = onPreviousListener,
        ) {
            Text("Previous")
        }
        Button(
            onClick = onSignUpListener,
            enabled = photoUri.toString().isNotBlank()
        ) {
            Text("Sign Up")
        }
    }
}
