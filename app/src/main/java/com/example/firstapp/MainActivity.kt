package com.example.firstapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.firstapp.ui.theme.FirstAppTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstAppTheme {
                TelaBoasVindas()
                }
            }
        Log.d("MainActivity", "onCreate"); //debug
    }

    override fun onStart() {
        super.onStart()
        Log.v("MainActivity", "onStart");//verbose
    }

    override fun onResume() {
        super.onResume()
        Log.w("MainActivity", "onResume");//warn
    }

    override fun onPause() {
        super.onPause()
        Log.e("MainActivity", "onPause"); //error
    }

    override fun onStop() {
        super.onStop()
        Log.wtf("MainActivity", "onStop"); //What a Terrible Failure
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("MainActivity", "onDestroy"); //verbose
    }
}

@Composable
fun TelaBoasVindas() {
    val context = LocalContext.current

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

        Button(onClick = {
            Toast.makeText(context, "Vamos começar!", Toast.LENGTH_SHORT).show()
            // Aqui no futuro você vai abrir a tela de login com Firebase
        }) {
            Text("Começar")
        }
    }
}


//inicializador para todas as funcs secundárias.
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirstAppTheme {
        Greeting("Android")
    }
}


