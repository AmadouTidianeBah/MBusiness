package com.tbmob.m_business.presentation.admin.status.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatusDateDialog(
    modifier: Modifier = Modifier,
    dateState: DatePickerState,
    date: String,
    onDateClicked: () -> Unit,
    isDialogShowed: Boolean,
    isOKEnabled: Boolean,
    onOkClicked: () -> Unit,
    onCancelClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable { onDateClicked() }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .shadow(
                    elevation = 2.dp,
                    spotColor = MaterialTheme.colorScheme.primary
                )
                .padding(16.dp)
        ) {
            Text(text = date)
        }

        if (isDialogShowed) {
            DatePickerDialog(
                modifier = modifier,
                onDismissRequest = {},
                confirmButton = {
                    TextButton(
                        onClick = onOkClicked,
                        enabled = isOKEnabled
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = onCancelClicked) { Text("Cancel") }
                }
            ) {
                DatePicker(state = dateState)
            }
        }
    }
}