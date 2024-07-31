package com.tbmob.m_business.presentation.admin

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PeopleAlt
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PeopleAlt
import androidx.compose.material.icons.outlined.PieChartOutline
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tbmob.m_business.presentation.admin.detail.AdminDetailScreen
import com.tbmob.m_business.presentation.admin.detail.AdminDetailViewModel
import com.tbmob.m_business.presentation.admin.home.AdminHomeScreen
import com.tbmob.m_business.presentation.admin.home.AdminHomeViewModel
import com.tbmob.m_business.presentation.admin.product.add_product.CreateProductScreen
import com.tbmob.m_business.presentation.admin.product.add_product.CreateProductViewModel
import com.tbmob.m_business.presentation.admin.product.home.ProductHome
import com.tbmob.m_business.presentation.admin.product.home.ProductViewModel
import com.tbmob.m_business.presentation.admin.status.StatusScreen
import com.tbmob.m_business.presentation.admin.status.StatusViewModel
import com.tbmob.m_business.presentation.admin.user.add_user.CreateUserScreen
import com.tbmob.m_business.presentation.admin.user.add_user.CreateUserViewModel
import com.tbmob.m_business.presentation.admin.user.home.HomeUserScreen
import com.tbmob.m_business.presentation.admin.user.home.HomeUserViewModel
import com.tbmob.m_business.presentation.ui.theme.MBusinessTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(
    modifier: Modifier = Modifier,
    onLogoutClicked: () -> Unit
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "Home"
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = getTopBarTitle(currentRoute),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.headlineLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)),
                actions = {
                    IconButton(onClick = onLogoutClicked) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.Logout,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                navigationItemItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.title,
                        onClick = {
                            navController.navigate(item.title) {
                                launchSingleTop = true
                                popUpTo("Home")
                            }
                        },
                        label = { Text(
                            text = item.title,
                            color = MaterialTheme.colorScheme.onPrimary
                        ) },
                        icon = { Icon(
                            imageVector = if (currentRoute == item.title) item.selectedIcon else item.unselectedIcon,
                            contentDescription = null
                        ) },
                        alwaysShowLabel = false,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "admin_home",
            modifier = Modifier
                .padding(innerPadding)
        ) {
            navigation(
                startDestination = "Home",
                route = "admin_home"
            ) {
                composable("Home") {
                    val viewModel: AdminHomeViewModel = hiltViewModel()
                    val users by viewModel.usersAndSold.collectAsState()
                    val sales by viewModel.sales.collectAsState()
                    val amount by viewModel.amount.collectAsState()

                    AdminHomeScreen(
                        usersAndSold = users?.distinctBy { it.user.username } ?: emptyList(),
                        sales = sales ?: emptyList(),
                        onUserStatusClick = {userAndSold ->
                            navController.navigate("Detail/${userAndSold.user.username}") {
                                launchSingleTop = true
                            }
                        },
                        amount = amount,
                        onSortedByDateClicked = { viewModel.sortedBy(AdminHomeViewModel.SortedBy.SortedByDate) },
                        onSortedByNameClicked = { viewModel.sortedBy(AdminHomeViewModel.SortedBy.SortedByName) }
                    )
                }

                composable(
                    route = "Detail/{username}",
                    arguments = listOf(
                        navArgument("username") {
                            type = NavType.StringType
                            defaultValue = ""
                        }
                    )
                ) {
                    val viewModel: AdminDetailViewModel = hiltViewModel()
                    val userAndSold by viewModel.usersAndSold.collectAsState()
                    val amount by viewModel.amount.collectAsState()

                    userAndSold?.first()?.user?.let { user ->
                        AdminDetailScreen(
                            user = user,
                            sales = userAndSold!!,
                            amount = amount,
                            onSortedByNameClicked = {viewModel.sortedBy(AdminDetailViewModel.SortedBy.SortedByName)},
                            onSortedByDateClicked = {viewModel.sortedBy(AdminDetailViewModel.SortedBy.SortedByDate)}
                        )
                    }
                }
            }

            navigation(
                startDestination = "Product",
                route = "admin_product"
            ) {
                composable("Product") {
                    val viewModel: ProductViewModel = hiltViewModel()
                    val products by viewModel.products.collectAsState()

                    ProductHome(
                        products = products?.reversed() ?: emptyList(),
                        onProductClicked = {product ->
                            navController.navigate("create_product?productName=${product.name}") {
                                launchSingleTop = true
                            }
                        },
                        onAddClicked = {
                            navController.navigate("create_product") {
                                launchSingleTop = true
                            }
                        },
                        onDeleteProductClicked = {product ->
                            viewModel.deleteProduct(product)
                        },
                        onRestoreProduct = {
                            viewModel.restoreProduct()
                        }
                    )
                }

                composable(
                    route = "create_product?productName={productName}",
                    arguments = listOf(
                        navArgument("productName") {
                            type = NavType.StringType
                            defaultValue = ""
                        })
                ) {
                    val viewModel: CreateProductViewModel = hiltViewModel()
                    val state by viewModel.state.collectAsState()

                    CreateProductScreen(
                        state = state,
                        onEvents = viewModel::onEvent,
                        enableCreateProductBtn = viewModel.areFieldsBlank(),
                        onCreateProductClicked = {
                            navController.navigateUp()
                        }
                    )
                }
            }

            navigation(
                startDestination = "Employee",
                route = "admin_user"
            ) {
                composable("Employee") {
                    val viewModel: HomeUserViewModel = hiltViewModel()
                    val users by viewModel.users.collectAsState()

                    HomeUserScreen(
                        users = users ?: emptyList(),
                        onUserClicked = {user ->
                            navController.navigate("create_user?userId=${user.username}") {
                                launchSingleTop = true
                            }
                        },
                        onDeleteUserClicked = {user ->
                            viewModel.deleteProduct(user)
                        },
                        onAddClicked = {
                            navController.navigate("create_user"){
                                launchSingleTop = true
                            }
                        },
                        onRestoreProduct = {
                            viewModel.restoreProduct()
                        },
                    )
                }

                composable(
                    route = "create_user?userId={userId}",
                    arguments = listOf(
                        navArgument("userId") {
                            type = NavType.StringType
                            defaultValue = ""
                        })
                ) {
                    val viewModel: CreateUserViewModel = hiltViewModel()
                    val state by viewModel.state.collectAsState()

                    CreateUserScreen(
                        state = state,
                        onEvents = viewModel::onEvent,
                        enableCreateUserBtn = viewModel.areFieldsBlank(),
                        onCreateUserClicked = {
                            if (state.hasError) {
                                Toast.makeText(
                                    context,
                                    "Oups password is not correct",
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                navController.navigateUp()
                            }
                        }
                    )
                }
            }

            composable("Status") {
                val viewModel: StatusViewModel = hiltViewModel()
                val users by viewModel.employees.collectAsState()
                val userSold by viewModel.userSold.collectAsState()
                val expend by viewModel.expended.collectAsState()
                val sold by viewModel.sold.collectAsState()
                val benefit by viewModel.benefit.collectAsState()
                val date by viewModel.date.collectAsState()

                StatusScreen(
                    users = users?.distinctBy { it.user.username } ?: emptyList(),
                    userSold = userSold ?: mapOf(),
                    expend = expend,
                    sold = sold,
                    benefit = benefit,
                    date = date,
                    onDateChanged = {viewModel.updateDate(it)},
                )
            }
        }
    }
}

private val navigationItemItems =
    listOf(
        NavigationItem("Home", Icons.Filled.Home, Icons.Outlined.Home),
        NavigationItem("Product", Icons.Filled.ShoppingCart, Icons.Outlined.ShoppingCart),
        NavigationItem("Employee", Icons.Filled.PeopleAlt, Icons.Outlined.PeopleAlt),
        NavigationItem("Status", Icons.Filled.PieChart, Icons.Outlined.PieChartOutline),
    )

private data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

private fun getTopBarTitle(route: String): String {
    return when(route) {
        "Detail/{username}" -> "Detail"
        "create_user?userId={userId}" -> "Add/Update Employee"
        "create_product?productName={productName}" -> "Add/Update Product"
        "Product" -> "Product"
        "Employee" -> "Employee"
        "Status" -> "Status"
        else -> "Home"
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AdminScreenPre() {
    MBusinessTheme {
//        AdminScreen()
    }
}