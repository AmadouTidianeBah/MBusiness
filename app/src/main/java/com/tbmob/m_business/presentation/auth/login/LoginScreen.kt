package com.tbmob.m_business.presentation.auth.login

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tbmob.m_business.presentation.common.MBusinessTextField
import com.tbmob.m_business.presentation.common.ScreenIntro
import com.tbmob.m_business.presentation.ui.theme.MBusinessTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    state: LoginState,
    onEvents: (LoginEvents) -> Unit,
    onLoginClicked: () -> Unit,
    onForgetPasswordClicked: () -> Unit,
    isLoginBtnEnabled: Boolean,
    onCreateAccountClicked: () -> Unit
) {
    val focus = LocalFocusManager.current

    Scaffold(
        modifier = modifier
    ) {innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(innerPadding)
                .padding(vertical = 32.dp, horizontal = 24.dp)
                .fillMaxSize()
                .scrollable(rememberScrollState(), Orientation.Vertical)
        ) {
            ScreenIntro(title = "Welcome Back!", subTitle = "Enter your username and password")
            Spacer(modifier = Modifier.height(52.dp))
            Column {
                MBusinessTextField(
                    value = state.username,
                    onValueChange = { onEvents(LoginEvents.OnUsernameChange(it)) },
                    label = "username",
                    imeAction = ImeAction.Next
                )
                Spacer(modifier = Modifier.height(28.dp))
                MBusinessTextField(
                    value = state.password,
                    onValueChange = { onEvents(LoginEvents.OnPasswordChange(it)) },
                    label = "password",
                    imeAction = ImeAction.Done,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardActions = KeyboardActions(onDone = {
                        focus.clearFocus()
                    })
                )
            }
            Spacer(modifier = Modifier.height(28.dp))
            Button(
                onClick = onLoginClicked,
                enabled = isLoginBtnEnabled,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Login", letterSpacing = 8.sp)
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(
                    onClick = onForgetPasswordClicked,
                    colors = ButtonDefaults.textButtonColors(contentColor = Color.Red.copy(alpha = .8f)),
                ) {
                    Text(text = "Forget password ?")
                }
                Spacer(modifier = Modifier.width(8.dp))
                TextButton(
                    onClick = onCreateAccountClicked,
                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.primary),
                ) {
                    Text(text = "Create Account")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginSignupPre() {
    MBusinessTheme {
//        LoginScreen()
    }
}