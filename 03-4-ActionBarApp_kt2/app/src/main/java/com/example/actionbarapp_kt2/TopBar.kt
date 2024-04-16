package com.example.actionbarapp_kt2

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onFavoriteClicked: () -> Unit = {},
    onItem1Clicked: () -> Unit = {},
    onItem2Clicked: () -> Unit = {},
    onSettingsClicked: () -> Unit = {}
) {
    var mDisplayMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        actions = {
            IconButton(onClick = onFavoriteClicked) {
                Icon(Icons.Default.Favorite, "Favorite")
            }
            IconButton(onClick = { mDisplayMenu = !mDisplayMenu }) {
                Icon(Icons.Default.MoreVert, "Menu")
            }

            DropdownMenu(
                expanded = mDisplayMenu,
                onDismissRequest = { mDisplayMenu = false }
            ) {
                DropdownMenuItem(
                    text = { Text(text = "Item 1") },
                    onClick = {
                        mDisplayMenu = false
                        onItem1Clicked()
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "Item 2") },
                    onClick = {
                        mDisplayMenu = false
                        onItem2Clicked()
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "Settings") },
                    onClick = {
                        mDisplayMenu = false
                        onSettingsClicked()
                    }
                )
            }
        }
    )
}