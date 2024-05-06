package com.artemissoftware.pokeconnect.core.designsystem.composables.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.artemissoftware.pokeconnect.R
import com.artemissoftware.pokeconnect.core.designsystem.PokeConnectTheme
import com.artemissoftware.pokeconnect.core.designsystem.ThemePreviews
import com.artemissoftware.pokeconnect.core.designsystem.spacing
import com.artemissoftware.pokeconnect.core.models.search.SearchResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PCSearchBar(
    text: String,
    placeHolderText: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onActiveChange: (Boolean) -> Unit,
    onClearSearch: () -> Unit,
    modifier: Modifier = Modifier,
    isSearching: Boolean = false,
    historyItems: List<SearchResult> = emptyList(),
) {
    SearchBar(
        modifier = modifier,
        query = text,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = isSearching,
        onActiveChange = onActiveChange,
        placeholder = {
            Text(
                text = placeHolderText,
                style = MaterialTheme.typography.bodySmall,
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
            )
        },
        trailingIcon = {
            AnimatedVisibility(visible = isSearching) {
                Icon(
                    modifier = Modifier.clickable {
                        onActiveChange(false)
                    },
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = null,
                )
            }
            AnimatedVisibility(visible = !isSearching && text.isNotEmpty()) {
                Icon(
                    modifier = Modifier.clickable {
                        onClearSearch()
                    },
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                )
            }
        },
    ) {
        historyItems.forEach { historyItem ->
            Row(
                modifier = Modifier
                    .padding(all = MaterialTheme.spacing.spacing2),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier.padding(end = MaterialTheme.spacing.spacing1_5),
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                )
                Text(
                    text = historyItem.entry(),
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        }
    }
}

@ThemePreviews
@Composable
private fun PCSearchBarPreview() {
    PokeConnectTheme {
        PCSearchBar(
            modifier = Modifier
                .fillMaxWidth(),
            text = "",
            placeHolderText = "Search",
            onQueryChange = { },
            onSearch = { },
            onActiveChange = { },
            onClearSearch = { },
        )
    }
}

@ThemePreviews
@Composable
private fun PCSearchBar_history_Preview() {
    PokeConnectTheme {
        PCSearchBar(
            modifier = Modifier
                .fillMaxWidth(),
            historyItems = listOf(SearchResult("1"), SearchResult("2", "Ivysaur")),
            text = "Theme 3",
            placeHolderText = "Search",
            isSearching = true,
            onQueryChange = { },
            onSearch = { },
            onActiveChange = { },
            onClearSearch = { },
        )
    }
}
