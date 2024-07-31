package com.tbmob.m_business.presentation.user.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.tbmob.m_business.data.local.entities.Product
import com.tbmob.m_business.data.local.entities.relations.SalesWithProductAndUser
import com.tbmob.m_business.presentation.user.home.components.HomeSoldStatus
import com.tbmob.m_business.presentation.user.home.components.HomeStats

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    products: List<Product>,
    sales: List<SalesWithProductAndUser>,
    onItemClicked: (SalesWithProductAndUser) -> Unit,
    sold: Map<String, Int>,
    navigateToSellScreen: () -> Unit,
    onLogoutClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(
                    text = "Home Screen",
                    color = MaterialTheme.colorScheme.onPrimary
                ) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                actions = {
                    IconButton(onClick = onLogoutClicked) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.Logout,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                },
                modifier = Modifier.clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = navigateToSellScreen) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
        modifier = modifier
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            HomeStats(
                products = products,
                sold = sold
            )
            HomeSoldStatus(sales = sales, onItemClicked = onItemClicked)
        }
    }
}