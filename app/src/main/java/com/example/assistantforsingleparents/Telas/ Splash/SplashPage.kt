package com.example.assistantforsingleparents.Telas.Splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.assistantforsingleparents.R
import com.example.assistantforsingleparents.ui.theme.AssistantForSingleParentsTheme



@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(3000L)  // Espera 3 segundos
        navController.navigate("login") {
            popUpTo("splash") { inclusive = true }  // Limpa a pilha de navegação
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Logo"
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Bem-vindo ao TaskBuddy!",
                color = Color.Black,
                style = MaterialTheme.typography.headlineMedium // Usando headlineMedium
            )
        }
    }
}

@Preview
@Composable
private fun preview() {
    AssistantForSingleParentsTheme {
        
    }
}











