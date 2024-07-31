package com.tbmob.m_business.presentation.admin.user.home

import android.annotation.SuppressLint
import android.util.Log
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
import com.tbmob.m_business.data.local.entities.User
import com.tbmob.m_business.presentation.admin.user.home.components.HomeUserItem
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeUserScreen(
    modifier: Modifier = Modifier,
    users: List<User>,
    onDeleteUserClicked: (User) -> Unit,
    onAddClicked: () -> Unit,
    onRestoreProduct: () -> Unit,
    onUserClicked: (User) -> Unit
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
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        modifier = modifier
    ) {
        LazyColumn {
            items(items = users, key = {it.username}) {user ->
                HomeUserItem(
                    user = user,
                    onUserClicked = onUserClicked,
                    onDeleteUserClicked = {
                        onDeleteUserClicked(user)
                        scope.launch {
                            val result = snackbarHostState
                                .showSnackbar(
                                    message = "Do you wanna restore the product ?",
                                    actionLabel = "Yes",
                                    duration = SnackbarDuration.Short
                                )
                            Log.d("ViewModel", "ProductHome: $result")
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