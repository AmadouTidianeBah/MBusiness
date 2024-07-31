package com.tbmob.m_business.presentation.admin.status

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jaikeerthick.composable_graphs.composables.pie.model.PieData
import com.tbmob.m_business.data.local.entities.relations.SalesWithProductAndUser
import com.tbmob.m_business.presentation.admin.status.components.StatusAmount
import com.tbmob.m_business.presentation.admin.status.components.StatusDateRow
import com.tbmob.m_business.presentation.admin.status.components.UsersStatus
import com.tbmob.m_business.presentation.ui.theme.MBusinessTheme

@Composable
fun StatusScreen(
    modifier: Modifier = Modifier,
    users: List<SalesWithProductAndUser>,
    userSold: Map<String, Double>,
    expend: Double,
    sold: Double,
    benefit: Double,
    date: Long,
    onDateChanged: (Long) -> Unit,
) {
    val context = LocalContext.current
    val pieChartData = listOf(
        PieData(value = 170F, label = "Expend", color = Color.Red, labelColor = Color.Black),
        PieData(value = 82F, label = "Sold", color = Color.Gray, labelColor = Color.Black),
        PieData(value = 150F, label = "Benefit", color = Color.Green, labelColor = Color.Black),
    )

    Column(
        modifier = modifier
            .padding(12.dp)
            .scrollable(rememberScrollState(), Orientation.Vertical)
    ) {
        StatusDateRow(
            date = date,
            onDateChanged = onDateChanged
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
//            PieChart(
//                modifier = Modifier
//                    .size(120.dp),
//                data = pieChartData,
//                onSliceClick = { pieData ->
//                    Toast.makeText(context, "${pieData.label}", Toast.LENGTH_SHORT).show()
//                },
//            )
//            Spacer(modifier = Modifier.width(20.dp))
            StatusAmount(expend = expend, sold = sold, benefit = benefit)
        }
        Text(text = "Employees", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(20.dp))
        UsersStatus(users = users, userSold = userSold)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun StatusScreenPre() {
    MBusinessTheme {
//        StatusScreen()
    }
}