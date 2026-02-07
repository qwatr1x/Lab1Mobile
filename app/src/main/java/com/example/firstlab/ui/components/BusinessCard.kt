package com.example.firstlab.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstlab.ui.theme.BusinessCardTheme
import com.example.firstlab.R
import com.example.firstlab.ui.theme.ContactTint
import com.example.firstlab.ui.theme.PrimaryDark

@Composable
fun BusinessCardScreen() {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

    val avatarSize = if (isLandscape) 100.dp else 140.dp
    val verticalPadding = if (isLandscape) 16.dp else 24.dp
    val spacingAfterAvatar = if (isLandscape) 20.dp else 28.dp
    val spacingAfterName = if (isLandscape) 6.dp else 8.dp
    val contactSpacing = if (isLandscape) 10.dp else 12.dp

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = verticalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            //Аватар
            Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = stringResource(id = R.string.full_name),
                modifier = Modifier
                    .size(avatarSize)
                    .clip(CircleShape)
                    .border(3.dp, MaterialTheme.colorScheme.primary, CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(spacingAfterAvatar))

            // Имя
            Text(
                text = stringResource(id = R.string.full_name),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(spacingAfterName))

            // Группа
            Text(
                text = stringResource(id = R.string.group),
                style = MaterialTheme.typography.titleMedium,
                color = PrimaryDark
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Контакты
            Column(
                verticalArrangement = Arrangement.spacedBy(contactSpacing),
                modifier = Modifier.fillMaxWidth()
            ) {
                ContactItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Email",
                            tint = ContactTint
                        )
                    },
                    text = stringResource(id = R.string.contact_email)
                )

                ContactItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = "Phone",
                            tint = ContactTint
                        )
                    },
                    text = stringResource(id = R.string.contact_phone)
                )

                ContactItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = "Phone",
                            tint = ContactTint
                        )
                    },
                    text = stringResource(id = R.string.contact_telegram)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun ContactItem(
    icon: @Composable () -> Unit,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            icon()
        }

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Medium
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun PreviewBusinessCardPortrait() {
    BusinessCardTheme {
        BusinessCardScreen()
    }
}

@Preview(showBackground = true, widthDp = 640, heightDp = 360)
@Composable
fun PreviewBusinessCardLandscape() {
    BusinessCardTheme {
        BusinessCardScreen()
    }
}