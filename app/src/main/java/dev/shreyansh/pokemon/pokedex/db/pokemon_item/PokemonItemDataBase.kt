package dev.shreyansh.pokemon.pokedex.db.pokemon_item

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = [PokemonItemEntity::class], exportSchema = false, version = 1)
abstract class PokemonItemDataBase : RoomDatabase() {

    abstract val pokemonItemDao: PokemonItemDao

    companion object {

        @Volatile
        private var INSTANCE: PokemonItemDataBase? = null

        fun getInstance(context: Context): PokemonItemDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PokemonItemDataBase::class.java,
                        "pokemon_items_db"
                    )
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}