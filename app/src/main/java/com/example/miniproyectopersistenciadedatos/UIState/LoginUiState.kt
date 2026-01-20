package com.example.miniproyectopersistenciadedatos.UIState

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class LoginUiState(
    val email: String = "",
    val contrasena: String = "",
    val recordarSesion: Boolean = false
)

class LoginViewModel : androidx.lifecycle.ViewModel() {
    var uiState by mutableStateOf(LoginUiState())
        private set

    fun onEmailChanged(newValue: String) { uiState = uiState.copy(email = newValue) }
    fun onContrasenaChanged(newValue: String) { uiState = uiState.copy(contrasena = newValue) }

    fun login() {
        // Aquí validarás con los datos de ROOM
        println("Login con: ${uiState.email}")
    }
}