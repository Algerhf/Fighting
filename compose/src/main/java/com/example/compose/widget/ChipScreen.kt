package com.example.compose.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ChipScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        AssistChip(
            leadingIcon = {
                Icon(Icons.Filled.Settings, contentDescription = null)
            },
            onClick = { },
            label = { Text(text = "AssistChip") }
        )

        var selected by remember {
            mutableStateOf(false)
        }

        FilterChip(
            selected = selected,
            onClick = { selected = !selected },
            label = { Text(text = "FilterChip") },
            leadingIcon = if (selected) {
                {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = null,
                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                    )
                }
            } else {
                null
            }
        )

        var enabled by remember {
            mutableStateOf(true)
        }

        if (enabled) {
            InputChip(selected = enabled,
                onClick = { enabled = !enabled },
                label = { Text(text = "InputChip") },
                avatar = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = null,
                        modifier = Modifier.size(InputChipDefaults.AvatarSize)
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                        modifier = Modifier.size(InputChipDefaults.AvatarSize)
                    )
                }
            )
        }

        SuggestionChip(
            onClick = {},
            label = { Text(text = "SuggestionChip") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChipScreenPreview() {
    ChipScreen()
}