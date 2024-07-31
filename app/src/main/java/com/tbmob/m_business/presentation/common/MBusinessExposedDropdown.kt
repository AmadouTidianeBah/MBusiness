package com.tbmob.m_business.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MBusinessExposedDropdown(
    modifier: Modifier = Modifier,
    dropdownItems: List<String>,
    selectedDropdown: String,
    selectedDropdownIcon: ByteArray?,
    onSelectedDropdownChange: (String) -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    if (selectedDropdownIcon != null) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it },
            modifier = modifier.fillMaxWidth()
        ) {
            TextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                value = selectedDropdown,
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                leadingIcon = {
                    ProductImage(
                        productImage = selectedDropdownIcon,
                        modifier = Modifier.size(36.dp)
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                dropdownItems.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item, style = MaterialTheme.typography.bodyLarge) },
                        onClick = {
                            onSelectedDropdownChange(item)
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        leadingIcon = {
                            ProductImage(
                                productImage = selectedDropdownIcon,
                                modifier = Modifier.size(36.dp)
                            )
                        }
                    )
                }
            }
        }
    } else {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it },
            modifier = modifier.fillMaxWidth()
        ) {
            TextField(
                modifier = Modifier.menuAnchor().fillMaxWidth(),
                value = selectedDropdown,
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                dropdownItems.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item, style = MaterialTheme.typography.bodyLarge) },
                        onClick = {
                            onSelectedDropdownChange(item)
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }
    }
}
