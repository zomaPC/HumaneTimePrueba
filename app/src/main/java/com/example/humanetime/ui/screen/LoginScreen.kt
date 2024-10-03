package com.example.humanetime.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.humanetime.R
import com.example.humanetime.navigation.Routes
import com.example.humanetime.ui.viewmodel.CodeDialog
import com.example.humanetime.ui.viewmodel.LoginViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainSpacer(height: Int) {
    Spacer(modifier = Modifier.height(height = height.dp))
}

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = koinViewModel(),
    navController: NavHostController
) {
    val errorMessage = loginViewModel.errorMessage.observeAsState().value ?: ""
    val loginSuccess = loginViewModel.loginSuccess.observeAsState().value ?: false
    val email = loginViewModel.email.observeAsState().value ?: ""
    val password = loginViewModel.password.observeAsState().value ?: ""
    val isButtonEnabled = email.isNotEmpty() && password.isNotEmpty()
    val isLoading = loginViewModel.isLoading.observeAsState(false).value

    LaunchedEffect(loginSuccess) {
        if (loginSuccess) {
            navController.navigate(Routes.ADMIN) {
                popUpTo(Routes.LOGIN) { inclusive = true }
            }
        }
    }
    if (errorMessage.isNotEmpty()) {
        CodeDialog(
            errorMessage = errorMessage,
            onDismiss = { loginViewModel.closeDialog() }
        )
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Image(
                painter = painterResource(id = R.drawable.loginto),
                contentDescription = "Logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )


            Text(
                text = stringResource(id = R.string.login_title),
                fontSize = 24.sp
            )


            OutlinedTextField(
                value = email,
                onValueChange = { loginViewModel.setEmail(it) },
                modifier = Modifier
                    .fillMaxWidth()
                ,
                label = {
                    Text(
                        text = stringResource(id = R.string.hint_email),
                        modifier = Modifier.alpha(0.5f)
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Gray
                )
            )


            OutlinedTextField(
                value = password,
                onValueChange = { loginViewModel.setPassword(it) },
                modifier = Modifier
                    .fillMaxWidth()
                ,
                label = {
                    Text(
                        text = stringResource(id = R.string.hint_password),
                        modifier = Modifier.alpha(0.5f)
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Gray
                )
            )

            // Botón de "Entrar"
            Button(
                onClick = { loginViewModel.login() },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                enabled = isButtonEnabled,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6200EE),
                    contentColor = Color.White
                )
            ) {
                if(isLoading){
                    CircularProgressIndicator(modifier = Modifier
                        .size(24.dp),
                        color = Color.Blue)
                }else{
                    Text(text = stringResource(id = R.string.login_button))

                }
            }

            // Mensajes adicionales
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(id = R.string.create_account),
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = stringResource(id = R.string.recover_password),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

