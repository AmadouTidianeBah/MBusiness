package com.tbmob.m_business.presentation.admin.product.home

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tbmob.m_business.data.local.entities.Product
import com.tbmob.m_business.presentation.admin.product.home.components.ProductItem
import com.tbmob.m_business.presentation.ui.theme.MBusinessTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductHome(
    modifier: Modifier = Modifier,
    products: List<Product>,
    onProductClicked: (Product) -> Unit,
    onAddClicked: () -> Unit,
    onDeleteProductClicked: (Product) -> Unit,
    onRestoreProduct: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClicked) {
                Icon(imageVector = Icons.Outlined.Add, contentDescription = null)
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState)},
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier
        ) {
            items(items = products, key = {it.name}) {product ->
                ProductItem(
                    product = product,
                    onProductClicked = onProductClicked,
                    onDeleteProductClicked = {
                        onDeleteProductClicked(product)
                        scope.launch {
                            val result = snackbarHostState
                                .showSnackbar(
                                    message = "Do you wanna restore the product ?",
                                    actionLabel = "Yes",
                                    duration = SnackbarDuration.Short
                                )
                            if (result == SnackbarResult.ActionPerformed) {
                                onRestoreProduct()
                            }
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ProductHomePre() {
    MBusinessTheme {
//        ProductHome()
    }
}