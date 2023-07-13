package dev.shreyansh.pokemon.pokedex.db.pokemon_quiz

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.shreyansh.pokemon.pokedex.utils.Converters



@Database(entities = [PokemonQuizEntity::class], exportSchema = false, version = 1)
@TypeConverters(Converters::class)

abstract class PokemonQuizDatabase : RoomDatabase() {

    abstract val pokemonQuizDao: PokemonQuizDao

    companion object {

        @Volatile
        private var INSTANCE: PokemonQuizDatabase? = null

        fun getInstance(context: Context): PokemonQuizDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PokemonQuizDatabase::class.java,
                        "pokemon_quiz_db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}