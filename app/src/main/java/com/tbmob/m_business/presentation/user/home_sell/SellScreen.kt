package com.tbmob.m_business.presentation.user.home_sell

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tbmob.m_business.data.local.entities.Product
import com.tbmob.m_business.presentation.common.MBusinessExposedDropdown

@Composable
fun SellScreen(
    modifier: Modifier = Modifier,
    products: List<Product>,
    onSelectedDropDownChange: (Product) -> Unit,
    onSelectedQuantityChange: (Int) -> Unit,
    onConfirmClicked: () -> Unit,
    onCancelClicked: () -> Unit,
    remaining: Int,
    isSellValid: Boolean
) {
    var selectedDropdown by remember { mutableStateOf<Product?>(null) }
    var selectedQuantity by remember { mutableIntStateOf(0) }

    Box(
       contentAlignment = Center,
        modifier = modifier.fillMaxSize()
    ) {
        Card(
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = modifier
                    .padding(20.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "Sell", style = MaterialTheme.typography.headlineLarge)
                }
                Spacer(modifier = Modifier.height(32.dp))
                MBusinessExposedDropdown(
                    dropdownItems = products.map { it.name },
                    selectedDropdown = if (selectedDropdown == null) "Select Product" else selectedDropdown?.name!!,
                    onSelectedDropdownChange = { title ->
                        selectedDropdown = products.find { it.name == title } ?: products.first()
                        onSelectedDropDownChange(selectedDropdown!!)
                    },
                    selectedDropdownIcon = selectedDropdown?.image,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Remaining:",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = if (remaining <= 0) "0" else remaining.toString(),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Quantity:",
                        style = MaterialTheme.typography.headlineMedium
                    )

                    MBusinessExposedDropdown(
                        dropdownItems = (1..remaining).toList().map { it.toString() },
                        selectedDropdown = selectedQuantity.toString()
                            .ifBlank { "Select Quantity" },
                        selectedDropdownIcon = null,
                        onSelectedDropdownChange = {
                            selectedQuantity = it.toInt()
                            onSelectedQuantityChange(selectedQuantity)
                        },
                        modifier = Modifier.width(100.dp)
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = onCancelClicked,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red.copy(red = .7f)),
                        modifier = Modifier.width(100.dp)
                    ) {
                        Text(text = "Cancel")
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Button(
                        onClick = onConfirmClicked,
                        enabled = isSellValid,
                        modifier = Modifier.width(100.dp)
                    ) {
                        Text(text = "Sell")
                    }
                }
            }
        }
    }
}