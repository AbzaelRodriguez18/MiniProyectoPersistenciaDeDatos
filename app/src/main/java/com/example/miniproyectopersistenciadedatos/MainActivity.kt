package com.example.miniproyectopersistenciadedatos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.miniproyectopersistenciadedatos.Pantallas.Pantalla1
import com.example.miniproyectopersistenciadedatos.Pantallas.PantallaCrear
import com.example.miniproyectopersistenciadedatos.Pantallas.PantallaLogin
import com.example.miniproyectopersistenciadedatos.data.AppDatabase
import com.example.miniproyectopersistenciadedatos.data.ManejadorDeAjustes
import com.example.miniproyectopersistenciadedatos.ui.theme.MiniProyectoPersistenciaDeDatosTheme
import com.example.miniproyectopersistenciadedatos.viewmodel.AppViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AppDatabase.getDatabase(applicationContext)
        val sm = ManejadorDeAjustes(applicationContext)
        val vm = AppViewModel(db.usuarioDao(), sm)

        enableEdgeToEdge()

        setContent {
            val oscuro by vm.darkTheme.collectAsState(initial = false)
            val navController = rememberNavController()

            MiniProyectoPersistenciaDeDatosTheme(darkTheme = oscuro) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") {
                            PantallaLogin(
                                viewModel = vm,
                                onNavigateToRegistro = { navController.navigate("registro") },
                                onLoginSuccess = { navController.navigate("pantalla1") }
                            )
                        }
                        composable("registro") {
                            PantallaCrear(
                                viewModel = vm,
                                onBack = { navController.navigate("pantalla1")
                                }
                            )
                        }
                        composable("pantalla1") {
                            Pantalla1(viewModel = vm)
                        }
                    }
                }


            }
        }
    }
}