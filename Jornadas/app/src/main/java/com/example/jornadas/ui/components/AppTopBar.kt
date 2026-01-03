package com.example.jornadas.ui.components

import android.R.attr.iconTint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.jornadas.R

@Composable
fun AppTopBar(
    isDarkMode: Boolean,
    modifier: Modifier = Modifier
        .statusBarsPadding()
        .clip(shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.jornadas_logo),
                contentDescription = "logo do app jornadas",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(48.dp)
            )

            Text(
                stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick = { }
            ) {
                if (isDarkMode) {
                    Icon(
                        imageVector = Icons.Default.LightMode,
                        contentDescription = stringResource(R.string.light_mode),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.DarkMode,
                        contentDescription = stringResource(R.string.dark_mode),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
            IconButton(
                onClick = {  }
            ) {
                Icon(
                    imageVector = Icons.Default.Logout,
                    contentDescription = "Sair da conta",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

