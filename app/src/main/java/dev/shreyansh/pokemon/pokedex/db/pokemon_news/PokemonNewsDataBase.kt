package dev.shreyansh.pokemon.pokedex.db.pokemon_news

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [PokemonNewsEntity::class], exportSchema = false, version = 1)
abstract class PokemonNewsDataBase : RoomDatabase() {

    abstract val pokemonNewsDao: PokemonNewsDao

    companion object {

        @Volatile
        private var INSTANCE: PokemonNewsDataBase? = null

        fun getInstance(context: Context): PokemonNewsDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PokemonNewsDataBase::class.java,
                        "pokemon_news_db"
                    )
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}