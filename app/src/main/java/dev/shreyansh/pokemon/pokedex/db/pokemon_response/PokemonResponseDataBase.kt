package dev.shreyansh.pokemon.pokedex.db.pokemon_response

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.shreyansh.pokemon.pokedex.utils.Converters


@Database(entities = [PokemonResponseEntity::class], exportSchema = false, version = 1)
@TypeConverters(Converters::class)
abstract class PokemonResponseDataBase : RoomDatabase() {

    abstract val pokemonResponseDao: PokemonResponseDao

    companion object {

        @Volatile
        private var INSTANCE: PokemonResponseDataBase? = null

        fun getInstance(context: Context): PokemonResponseDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PokemonResponseDataBase::class.java,
                        "pokemon_response_db"
                    )
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}