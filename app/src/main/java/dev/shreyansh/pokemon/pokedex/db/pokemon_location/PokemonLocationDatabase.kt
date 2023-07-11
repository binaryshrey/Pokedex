package dev.shreyansh.pokemon.pokedex.db.pokemon_location

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [PokemonLocationEntity::class], exportSchema = false, version = 1)
abstract class PokemonLocationDatabase : RoomDatabase() {

    abstract val pokemonLocationDao: PokemonLocationDao

    companion object {

        @Volatile
        private var INSTANCE: PokemonLocationDatabase? = null

        fun getInstance(context: Context): PokemonLocationDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PokemonLocationDatabase::class.java,
                        "pokemon_locations_db"
                    )
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}