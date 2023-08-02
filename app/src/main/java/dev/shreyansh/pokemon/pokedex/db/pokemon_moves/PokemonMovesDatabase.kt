package dev.shreyansh.pokemon.pokedex.db.pokemon_moves

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.shreyansh.pokemon.pokedex.utils.MovesConvertor


@Database(entities = [PokemonMovesEntity::class], exportSchema = false, version = 1)
@TypeConverters(MovesConvertor::class)

abstract class PokemonMovesDatabase : RoomDatabase() {

    abstract val pokemonMovesDao: PokemonMovesDao

    companion object {

        @Volatile
        private var INSTANCE: PokemonMovesDatabase? = null

        fun getInstance(context: Context): PokemonMovesDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PokemonMovesDatabase::class.java,
                        "pokemon_moves_db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}