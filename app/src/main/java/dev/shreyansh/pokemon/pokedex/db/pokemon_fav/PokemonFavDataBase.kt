package dev.shreyansh.pokemon.pokedex.db.pokemon_fav

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.shreyansh.pokemon.pokedex.utils.Converters




@Database(entities = [PokemonFavEntity::class], exportSchema = false, version = 1)
@TypeConverters(Converters::class)
abstract class PokemonFavDataBase : RoomDatabase() {

    abstract val pokemonFavDao: PokemonFavDao

    companion object {

        @Volatile
        private var INSTANCE: PokemonFavDataBase? = null

        fun getInstance(context: Context): PokemonFavDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PokemonFavDataBase::class.java,
                        "pokemon_fav_db"
                    )
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}