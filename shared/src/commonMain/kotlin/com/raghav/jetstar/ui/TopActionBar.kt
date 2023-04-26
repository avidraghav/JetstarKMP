package com.raghav.jetstar.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TopActionBar(
    modifier: Modifier = Modifier,
    showSearchIcon: Boolean = true,
    onHamburgerIconClicked: () -> Unit = {},
    onJetstarIconClicked: () -> Unit = {},
    onSearchIconClicked: () -> Unit = {}

) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                IconButton(onClick = { onHamburgerIconClicked() }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null
                    )
                }
                IconButton(onClick = { onJetstarIconClicked() }) {
                    Icon(
                        imageVector = Icons.Default.Face,
                        contentDescription = null
                    )
                }
            }
            if (showSearchIcon) {
                IconButton(onClick = { onSearchIconClicked() }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

// TopAppBar(
// title = {
//    IconButton(onClick = {}) {
//        Icon(imageVector = Icons.Default.Menu, contentDescription = null)
//    }
// },
// backgroundColor = MaterialTheme.colors.background.copy(alpha = 0.6f),
// contentColor = MaterialTheme.colors.onBackground,
// navigationIcon = {
//    if (showSearchIcon) {
//        IconButton(onClick = {}) {
//            Icon(imageVector = Icons.Default.Search, contentDescription = null)
//        }
//    }
// },
// modifier = modifier
// )
