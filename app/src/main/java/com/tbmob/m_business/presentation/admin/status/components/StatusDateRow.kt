package com.tbmob.m_business.presentation.admin.status.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tbmob.m_business.core.dateFormater

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatusDateRow(
    modifier: Modifier = Modifier,
    date: Long,
    onDateChanged: (Long) -> Unit,
    ) {
    val dateState = rememberDatePickerState()
    val confirmEnabled by remember {
        derivedStateOf { dateState.selectedDateMillis != null }
    }
    var isDateShowed by rememberSaveable {
        mutableStateOf(false)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 28.dp)
    ) {
        StatusDateDialog(
            dateState = dateState,
            date = dateFormater(date, "dd/MM/yyyy"),
            onDateClicked = { isDateShowed = true },
            isDialogShowed = isDateShowed,
            isOKEnabled = confirmEnabled,
            onOkClicked = {
                isDateShowed = false
                onDateChanged(dateState.selectedDateMillis!!)
            },
            onCancelClicked = { isDateShowed = false }
        )
        Text(
            text = "-",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Text(text = dateFormater(System.currentTimeMillis(), "dd/MM/yyyy"), color = Color.Gray)
    }
}