package com.example.firstapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.*
import com.example.firstapp.ui.theme.FirstAppTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import androidx.compose.animation.*
import androidx.compose.runtime.remember
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FirstAppTheme {
                AppNavigator()
            }
        }
    }

    override fun onStart() { super.onStart(); Log.v("MainActivity", "onStart") }
    override fun onResume() { super.onResume(); Log.w("MainActivity", "onResume") }
    override fun onPause() { super.onPause(); Log.e("MainActivity", "onPause") }
    override fun onStop() { super.onStop(); Log.wtf("MainActivity", "onStop") }
    override fun onDestroy() { super.onDestroy(); Log.v("MainActivity", "onDestroy") }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    AnimatedNavHost(
        navController = navController,
        startDestination = "boas_vindas",
        enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn() },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut() },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn() },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut() }
    ) {
        composable("boas_vindas") {
            TelaBoasVindas(onComecar = {
                navController.navigate("login")
            })
        }
        composable("login") {
            LoginScreen(onLoginSuccess = {
                navController.navigate("preferencias")
            })
        }
        composable("preferencias") {
            PreferenciasScreen(onVoltar = {
                navController.popBackStack()
            })
        }
    }
}

@Composable
fun TelaBoasVindas(onComecar: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Um app de mulheres e para todas as mulheres.",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Vamos juntas em segurança?",
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onComecar) {
            Text("Começar")
        }
    }
}
