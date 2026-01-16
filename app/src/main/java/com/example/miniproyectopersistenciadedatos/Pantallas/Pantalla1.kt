package com.example.miniproyectopersistenciadedatos.Pantallas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.miniproyectopersistenciadedatos.ui.theme.MiniProyectoPersistenciaDeDatosTheme

class Pantalla1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniProyectoPersistenciaDeDatosTheme {
                Pantalla1()

            }
        }
    }
}


@Composable
fun Pantalla1(modifier: Modifier = Modifier) {

}
@Preview(showBackground = true)
@Composable
fun PantallaPreview() {
    MiniProyectoPersistenciaDeDatosTheme {
        PantallaCrear()
    }
}