package com.example.firstapp

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.firstapp.model.PreferenciasRota
import com.example.firstapp.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreferenciasScreen(onVoltar: () -> Unit) {
    val context = LocalContext.current
    val user = FirebaseAuth.getInstance().currentUser
    val db = Firebase.firestore

    var bairroSaida by remember { mutableStateOf("") }
    var bairroDestino by remember { mutableStateOf("") }
    var horarioPreferido by remember { mutableStateOf("") }

    // Carregar preferências salvas
    LaunchedEffect(user) {
        user?.let {
            db.collection("usuarios").document(it.uid).get()
                .addOnSuccessListener { document ->
                    document.toObject(User::class.java)?.preferencias?.let { prefs ->
                        bairroSaida = prefs.bairroSaida
                        bairroDestino = prefs.bairroDestino
                        horarioPreferido = prefs.horarioPreferido
                    }
                }
        }
    }

    val horariosSugeridos = listOf("21:00", "21:30", "22:00", "22:30", "23:00")
    var horarioExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Preferências de Rota", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = bairroSaida,
            onValueChange = { bairroSaida = it },
            label = { Text("Bairro de Saída") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = bairroDestino,
            onValueChange = { bairroDestino = it },
            label = { Text("Bairro de Destino") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Dropdown de horário preferido
        ExposedDropdownMenuBox(
            expanded = horarioExpanded,
            onExpandedChange = { horarioExpanded = !horarioExpanded }
        ) {
            OutlinedTextField(
                value = horarioPreferido,
                onValueChange = {},
                readOnly = true,
                label = { Text("Horário Preferido") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = horarioExpanded)
                },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = horarioExpanded,
                onDismissRequest = { horarioExpanded = false }
            ) {
                horariosSugeridos.forEach { horario ->
                    DropdownMenuItem(
                        text = { Text(horario) },
                        onClick = {
                            horarioPreferido = horario
                            horarioExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            if (user != null) {
                val novaPreferencia = PreferenciasRota(
                    bairroSaida = bairroSaida,
                    bairroDestino = bairroDestino,
                    horarioPreferido = horarioPreferido
                )

                db.collection("usuarios")
                    .document(user.uid)
                    .update("preferencias", novaPreferencia)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Preferências salvas!", Toast.LENGTH_SHORT).show()
                        onVoltar()
                    }
                    .addOnFailureListener {
                        Toast.makeText(context, "Erro ao salvar: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }) {
            Text("Salvar Preferências")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(onClick = onVoltar) {
            Text("Voltar")
        }
    }
}

