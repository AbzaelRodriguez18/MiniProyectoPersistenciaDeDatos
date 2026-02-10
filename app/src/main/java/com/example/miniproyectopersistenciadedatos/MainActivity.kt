package com.example.miniproyectopersistenciadedatos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.miniproyectopersistenciadedatos.Pantallas.AppNavigation
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


        setContent {
            val oscuro by vm.darkTheme.collectAsState(initial = false)
            val navController = rememberNavController()

            MiniProyectoPersistenciaDeDatosTheme(darkTheme = oscuro) {

                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(
                        navController = navController, vm = vm
                    )
                }
            }
        }
    }
}