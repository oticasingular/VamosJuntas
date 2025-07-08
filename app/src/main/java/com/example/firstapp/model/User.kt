package com.example.firstapp.model

data class User(
    val uid: String = "",
    val nome: String = "",
    val email: String = "",
    val preferencias: PreferenciasRota = PreferenciasRota()
)
