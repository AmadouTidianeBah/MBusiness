package com.tbmob.m_business.presentation.admin.user.add_user

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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tbmob.m_business.presentation.common.MBusinessTextField
import com.tbmob.m_business.presentation.common.ScreenIntro
import com.tbmob.m_business.presentation.ui.theme.MBusinessTheme

@Composable
fun CreateUserScreen(
    modifier: Modifier = Modifier,
    state: CreateUserState,
    onEvents: (CreateUserEvents) -> Unit,
    enableCreateUserBtn: Boolean,
    onCreateUserClicked: () -> Unit
) {
    val focus = LocalFocusManager.current

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(vertical = 32.dp, horizontal = 24.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ScreenIntro(title = "Create User here", subTitle = "Enter the user information")
        Spacer(modifier = Modifier.height(52.dp))
        Column {
            MBusinessTextField(
                value = state.fullName,
                onValueChange = { onEvents(CreateUserEvents.OnFullNameChange(it)) },
                label = "Full-Name",
                imeAction = ImeAction.Next,
            )
            Spacer(modifier = Modifier.height(28.dp))
            MBusinessTextField(
                value = state.username,
                onValueChange = { onEvents(CreateUserEvents.OnUsernameChange(it)) },
                label = "Username",
                imeAction = ImeAction.Next,
            )
            Spacer(modifier = Modifier.height(28.dp))
            MBusinessTextField(
                value = state.password,
                onValueChange = { onEvents(CreateUserEvents.OnPasswordChange(it)) },
                label = "Password",
                visualTransformation = PasswordVisualTransformation(),
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next,
            )
            Spacer(modifier = Modifier.height(28.dp))
            MBusinessTextField(
                value = state.confirmPassword,
                onValueChange = { onEvents(CreateUserEvents.OnConfirmPasswordChange(it)) },
                label = "Confirm-Password",
                visualTransformation = PasswordVisualTransformation(),
                keyboardActions = KeyboardActions(onDone = {
                    focus.clearFocus()
                }),
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Checkbox(checked = state.isAdmin, onCheckedChange = { onEvents(
                CreateUserEvents.OnAdminChecked(
                    it
                )
            ) })
            Text(text = "Are you Admin ?", fontSize = 12.sp)
        }
        Button(
            onClick = {
                onEvents(CreateUserEvents.OnCreateUserClicked)
                onCreateUserClicked()
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = enableCreateUserBtn
        ) {
            Text(text = "Add User", letterSpacing = 8.sp)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SignupPre() {
    MBusinessTheme {
//        SignupScreen()
    }
}