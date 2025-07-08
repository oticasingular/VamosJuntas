package com.example.firstapp

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.firstapp.model.PreferenciasRota
import com.example.firstapp.model.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    val context = LocalContext.current
    val activity = context as Activity
    val auth = FirebaseAuth.getInstance()
    val db = Firebase.firestore

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.result
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            auth.signInWithCredential(credential)
                .addOnCompleteListener { authTask ->
                    if (authTask.isSuccessful) {
                        Toast.makeText(context, "Seja bem-vinda 💜", Toast.LENGTH_SHORT).show()
                        salvarUsuariaNoFirestore()
                        onLoginSuccess()
                    } else {
                        Toast.makeText(context, "Falha no login", Toast.LENGTH_SHORT).show()
                        Log.e("Login", "Erro: ${authTask.exception?.message}")
                    }
                }
        } catch (e: Exception) {
            Toast.makeText(context, "Erro no Sign-In: ${e.message}", Toast.LENGTH_SHORT).show()
            Log.e("Login", "Exceção: ${e.message}")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Vamos Juntas", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            val googleSignInClient = GoogleSignIn.getClient(context, gso)
            launcher.launch(googleSignInClient.signInIntent)
        }) {
            Text("Entrar com Google")
        }
    }
}

private fun salvarUsuariaNoFirestore() {
    val user = FirebaseAuth.getInstance().currentUser ?: return
    val db = Firebase.firestore

    val usuaria = User(
        uid = user.uid,
        nome = user.displayName ?: "",
        email = user.email ?: "",
        preferencias = PreferenciasRota(
            bairroSaida = "Centro",
            bairroDestino = "Bigorrilho",
            horarioPreferido = "22:00"
        )
    )

    db.collection("usuarios").document(user.uid)
        .set(usuaria)
        .addOnSuccessListener {
            Log.d("Firestore", "Usuária salva com sucesso!")
        }
        .addOnFailureListener { e ->
            Log.e("Firestore", "Erro ao salvar: ${e.message}")
        }
}
