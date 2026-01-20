package com.example.miniproyectopersistenciadedatos.data
import androidx.room.*

@Dao
interface UsuarioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(usuario: Usuario)
    @Query("SELECT * FROM usuarios WHERE email = :email LIMIT 1")
    suspend fun obtenerUsuarioPorEmail(email: String): Usuario?
    @Query("SELECT * FROM usuarios WHERE email = :email AND contrasena = :pass LIMIT 1")
    suspend fun login(email: String, pass: String): Usuario?
}