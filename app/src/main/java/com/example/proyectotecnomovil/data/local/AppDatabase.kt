package com.example.proyectotecnomovil.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProductorFavoritoEntity::class, ProductoFavoritoEntity::class, NotificationEntity::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productorDao(): ProductorFavoritoDao
    abstract fun productoDao(): ProductoFavoritoDao
    abstract fun notificacionDao(): NotificationDao

    /*
    Todo lo que está dentro del companion object sirve para un solo propósito:
    Asegurar que solo exista UNA conexión a la base de datos abierta al mismo tiempo
    */
    companion object {
        //@Volatile: Si un hilo (thread) escribe algo en esta variable INSTANCE, se asegúra de que todos
        //los demás hilos se enteren inmediatamente
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            //Si INSTANCE ya existe la retorna, sino sigue con lo de la derecha
            //synchronized: obliga a que pasen de a uno, como una fila
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "manos_locales_db"
                ).fallbackToDestructiveMigration() // Esto permite borrar la DB vieja si se cambia la versión
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}