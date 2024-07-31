package com.tbmob.m_business

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.tbmob.m_business.presentation.admin.AdminScreen
import com.tbmob.m_business.presentation.admin.user.add_user.CreateUserScreen
import com.tbmob.m_business.presentation.admin.user.add_user.CreateUserViewModel
import com.tbmob.m_business.presentation.auth.forget_password.ForgetPassword
import com.tbmob.m_business.presentation.auth.login.LoginScreen
import com.tbmob.m_business.presentation.auth.login.LoginViewModel
import com.tbmob.m_business.presentation.ui.theme.MBusinessTheme
import com.tbmob.m_business.presentation.user.detail.DetailScreen
import com.tbmob.m_business.presentation.user.detail.DetailViewModel
import com.tbmob.m_business.presentation.user.home.HomeScreen
import com.tbmob.m_business.presentation.user.home.HomeViewModel
import com.tbmob.m_business.presentation.user.home_sell.SellScreen
import com.tbmob.m_business.presentation.user.home_sell.SellViewModel
import dagger.hilt.android.AndroidEntryPoint

//INSERT INTO User (fullName, username, password, admin)
//VALUES ('amadou bah', 'amadou27', '12345', 1);

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MBusinessTheme {
                val navController = rememberNavController()
                val mainViewModel: MainViewModel = hiltViewModel()
                val username by mainViewModel.userId.collectAsState()
                val context = LocalContext.current

                NavHost(navController = navController, startDestination = "auth") {
                    navigation(
                        startDestination = "login",
                        route = "auth"
                    ) {
                        composable("sign_up") {
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

                        composable("login") {
                            val viewModel: LoginViewModel = hiltViewModel()
                            val state by viewModel.state.collectAsState()

                            LoginScreen(
                                state = state,
                                onEvents = viewModel::onEvent,
                                onLoginClicked = {
                                    viewModel.login(state.username, state.password) {user ->
                                        if (user != null) {
                                            mainViewModel.setupUserId(user.username)
                                            if (user.admin) {
                                                navController.navigate("admin") {
                                                    launchSingleTop = true
                                                    popUpTo("auth") {
                                                        inclusive = true
                                                    }
                                                }
                                            } else {
                                                navController.navigate("employee") {
                                                    launchSingleTop = true
                                                    popUpTo("auth") {
                                                        inclusive = true
                                                    }
                                                }
                                            }
                                        } else {
                                            Toast.makeText(
                                                this@MainActivity,
                                                "Oups the username or password is not correct",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    }
                                },
                                onForgetPasswordClicked = {
                                    navController.navigate("forgetPassword") {
                                        launchSingleTop = true
                                    }
                                },
                                onCreateAccountClicked = {
                                    navController.navigate("sign_up") {
                                        launchSingleTop = true
                                    }
                                },
                                isLoginBtnEnabled = viewModel.areFieldsBlank(),
                            )
                        }

                        composable("forgetPassword") {
                            ForgetPassword()
                        }
                    }

                    navigation(
                        startDestination = "employee_home",
                        route = "employee"
                    ) {
                        composable("employee_home") {
                            val viewModel: HomeViewModel = hiltViewModel()
                            val state by viewModel.state.collectAsState()
                            val sold by viewModel.productSoldQuantity.collectAsState()

                            viewModel.updateUsername(username ?: "")
                            HomeScreen(
                                products = state.products,
                                sales = state.sales,
                                onItemClicked = {sale ->
                                    navController.navigate("employee_detail/$username/${sale.product.name}") {
                                        launchSingleTop = true
                                    }
                                },
                                sold = sold,
                                navigateToSellScreen = {
                                    navController.navigate("sell_screen/$username") {
                                        launchSingleTop = true
                                    }
                                },
                                onLogoutClicked = {
                                    navController.navigateUp()
                                }
                            )
                        }

                        composable(
                            "sell_screen/{username}",
                            arguments = listOf(
                                navArgument("username") {
                                    type = NavType.StringType
                                    defaultValue = ""
                                }
                            )
                        ) {
                            val viewModel: SellViewModel = hiltViewModel()
                            val state by viewModel.state.collectAsState()

                            SellScreen(
                                products = state.products,
                                onSelectedDropDownChange = {product ->
                                    viewModel.updateRemaining(
                                        product.quantity - (state.soldQuantity[product.name] ?: 0)
                                    )
                                    viewModel.updateSaleToSaveName(product.name)
                                },
                                onSelectedQuantityChange = {quantity ->
                                    viewModel.updateSaleToSaveQuantity(quantity)
                                },
                                onConfirmClicked = {
                                    viewModel.saveSale()
                                    navController.navigateUp()
                                },
                                onCancelClicked = {
                                    navController.navigateUp()
                                },
                                remaining = state.remaining,
                                isSellValid = viewModel.isSellValid()
                            )
                        }

                        composable(
                            "employee_detail/{username}/{salesId}",
                            arguments = listOf(
                                navArgument("username") {
                                    type = NavType.StringType
                                },
                                navArgument("salesId") {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val viewModel: DetailViewModel = hiltViewModel()
                            val product by viewModel.sales.collectAsState()
                            val sold by viewModel.productSoldQuantity.collectAsState()
                            val remaining by viewModel.remaining.collectAsState()

                            product?.let { sales ->
                                DetailScreen(
                                    sales = sales,
                                    sale = sales.first(),
                                    sold = sold,
                                    remaining = remaining,
                                )
                            }
                        }
                    }

                    navigation(
                        startDestination = "home",
                        route = "admin"
                    ) {
                        composable(route = "home") { AdminScreen(onLogoutClicked = {
                            navController.navigateUp()
                        }) }
                    }
                }
            }
        }
    }
}
