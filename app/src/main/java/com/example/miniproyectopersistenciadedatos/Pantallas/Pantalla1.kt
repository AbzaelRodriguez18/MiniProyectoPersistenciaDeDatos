package com.example.miniproyectopersistenciadedatos.Pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.miniproyectopersistenciadedatos.viewmodel.AppViewModel

@Composable
fun Pantalla1(viewModel: AppViewModel) {
    // Observamos los cambios de DataStore
    val isDark by viewModel.darkTheme.collectAsState(initial = false)
    val idiomaActual by viewModel.idioma.collectAsState(initial = "Español")

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Ajustes de la Aplicación", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Modo Oscuro")
            Spacer(modifier = Modifier.width(10.dp))
            Switch(
                checked = isDark,
                onCheckedChange = { nuevoValor ->
                    viewModel.guardarAjustes(nuevoValor, idiomaActual)
                }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text("Idioma: $idiomaActual")
        Row {
            Button(onClick = { viewModel.guardarAjustes(isDark, "Español") }) {
                Text("Español")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(onClick = { viewModel.guardarAjustes(isDark, "English") }) {
                Text("English")
            }
        }
    }
}