package com.tbmob.m_business.presentation.auth.forget_password

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tbmob.m_business.presentation.common.ScreenIntro
import com.tbmob.m_business.presentation.ui.theme.MBusinessTheme

@Composable
fun ForgetPassword(
    modifier: Modifier = Modifier
) {
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
            ScreenIntro(
                title = "Do you forget your password ?",
                subTitle = "Enter your full-name and username below"
            )
            Spacer(modifier = Modifier.height(52.dp))
            Column {
                OutlinedTextField(
                    value = "",
                    onValueChange = {  },
                    label = { Text(text = "full-name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = {  },
                    label = { Text(text = "username") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = {

                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "Check Account", letterSpacing = 8.sp)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ForgetPre() {
    MBusinessTheme {
        ForgetPassword()
    }
}