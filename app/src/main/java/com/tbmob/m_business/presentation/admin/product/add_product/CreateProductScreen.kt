package com.tbmob.m_business.presentation.admin.product.add_product

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tbmob.m_business.presentation.admin.common.AdminImagePicker
import com.tbmob.m_business.presentation.common.MBusinessTextField
import com.tbmob.m_business.presentation.common.ScreenIntro

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CreateProductScreen(
    modifier: Modifier = Modifier,
    state: CreateProductState,
    onEvents: (CreateProductEvents) -> Unit,
    enableCreateProductBtn: Boolean,
    onCreateProductClicked: () -> Unit
) {
    val focus = LocalFocusManager.current

    Scaffold(
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(vertical = 32.dp, horizontal = 24.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            ScreenIntro(title = "Create Product here", subTitle = "Enter the product information")
            Spacer(modifier = Modifier.height(48.dp))
            Column {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    AdminImagePicker(
                        image = state.image,
                        onImageSelected = {
                            onEvents(
                                CreateProductEvents.OnImageChange(
                                    it
                                )
                            )
                        }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                MBusinessTextField(
                    value = state.name,
                    onValueChange = { onEvents(CreateProductEvents.OnNameChange(it)) },
                    label = "Name",
                    imeAction = ImeAction.Next,
                )
                Spacer(modifier = Modifier.height(16.dp))
                MBusinessTextField(
                    value = state.quantity,
                    onValueChange = { onEvents(CreateProductEvents.OnQuantityChange(it)) },
                    label = "Quantity",
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                )
                Spacer(modifier = Modifier.height(16.dp))
                MBusinessTextField(
                    value = state.basePrice,
                    onValueChange = { onEvents(CreateProductEvents.OnBasePriceChange(it)) },
                    label = "Base-Price",
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                )
                Spacer(modifier = Modifier.height(16.dp))
                MBusinessTextField(
                    value = state.sellPrice,
                    onValueChange = { onEvents(CreateProductEvents.OnSellPriceChange(it)) },
                    label = "Sell-Price",
                    keyboardActions = KeyboardActions(onDone = {
                        focus.clearFocus()
                    }),
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                )
            }
            Spacer(modifier = Modifier.height(28.dp))
            Button(
                onClick = {
                    onEvents(CreateProductEvents.OnCreateProductClicked)
                    onCreateProductClicked()
                },
                enabled = enableCreateProductBtn,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "Add Product", letterSpacing = 8.sp)
            }
        }
    }
}
