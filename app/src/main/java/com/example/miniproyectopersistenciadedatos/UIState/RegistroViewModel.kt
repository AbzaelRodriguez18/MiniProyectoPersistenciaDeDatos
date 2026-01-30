package com.example.miniproyectopersistenciadedatos.UIState

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class RegistroUiState(
    val nombre: String = "",
    val email: String = "",
    val contrasena: String = ""
)

class RegistroViewModel : androidx.lifecycle.ViewModel() {
    var uiState by mutableStateOf(RegistroUiState())
        private set

    fun onNombreChanged(newValue: String) { uiState = uiState.copy(nombre = newValue) }
    fun onEmailChanged(newValue: String) { uiState = uiState.copy(email = newValue) }
    fun onContrasenaChanged(newValue: String) { uiState = uiState.copy(contrasena = newValue) }

    fun registrarUsuario() {
        println("Usuario registrado: ${uiState.nombre}")
    }
}