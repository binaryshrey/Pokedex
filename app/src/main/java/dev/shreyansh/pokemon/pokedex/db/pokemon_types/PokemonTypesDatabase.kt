package dev.shreyansh.pokemon.pokedex.db.pokemon_types

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [PokemonTypesEntity::class], exportSchema = false, version = 1)

abstract class PokemonTypesDatabase : RoomDatabase() {

    abstract val pokemonTypesDao : PokemonTypesDao

    companion object {

        @Volatile
        private var INSTANCE: PokemonTypesDatabase? = null

        fun getInstance(context: Context): PokemonTypesDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PokemonTypesDatabase::class.java,
                        "pokemon_types_db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}