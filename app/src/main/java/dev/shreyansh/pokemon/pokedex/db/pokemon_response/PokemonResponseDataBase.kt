package dev.shreyansh.pokemon.pokedex.db.pokemon_response

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [PokemonResponseEntity::class], exportSchema = false, version = 1)
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