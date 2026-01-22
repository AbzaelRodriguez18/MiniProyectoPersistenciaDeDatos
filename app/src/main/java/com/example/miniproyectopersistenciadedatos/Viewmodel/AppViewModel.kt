package com.example.miniproyectopersistenciadedatos.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miniproyectopersistenciadedatos.data.Usuario
import com.example.miniproyectopersistenciadedatos.data.UsuarioDao
import com.example.miniproyectopersistenciadedatos.data.ManejadorDeAjustes
import kotlinx.coroutines.launch

class AppViewModel(private val dao: UsuarioDao, private val settings: ManejadorDeAjustes) : ViewModel() {

    var nombre by mutableStateOf("")
    var email by mutableStateOf("")
    var pass by mutableStateOf("")

    val darkTheme = settings.ModoOscuro
    val idioma = settings.idioma

    var mensajeError by mutableStateOf("")
    fun registrar(onSuccess: () -> Unit) {
        viewModelScope.launch {
            dao.insertar(Usuario(nombre = nombre, email = email, contrasena = pass))
            nombre = ""; email = ""; pass = ""
            onSuccess()
        }
    }

    fun intentarLogin(onLoginSuccess: () -> Unit) {
        viewModelScope.launch {
            // Primero buscamos si el correo existe (necesitamos un nuevo método en el DAO o filtrar aquí)
            // Por simplicidad, buscaremos el usuario por email
            val usuarioEncontrado = dao.obtenerUsuarioPorEmail(email)

            when {
                usuarioEncontrado == null -> {
                    mensajeError = "Este correo no está registrado"
                }
                usuarioEncontrado.contrasena != pass -> {
                    mensajeError = "Contraseña incorrecta"
                }
                else -> {
                    mensajeError = "" // Limpiar errores
                    onLoginSuccess()
                }
            }
        }
    }

    fun guardarAjustes(isDark: Boolean, lang: String) {
        viewModelScope.launch {
            settings.saveSettings(isDark, lang)
        }
    }
}