package dev.shreyansh.pokemon.pokedex.db.pokemon_ability

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.shreyansh.pokemon.pokedex.db.pokemon_news.PokemonNewsEntity


@Database(entities = [PokemonAbilityEntity::class], exportSchema = false, version = 1)
abstract class PokemonAbilityDataBase : RoomDatabase() {

    abstract val pokemonAbilityDao: PokemonAbilityDao

    companion object {

        @Volatile
        private var INSTANCE: PokemonAbilityDataBase? = null

        fun getInstance(context: Context): PokemonAbilityDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PokemonAbilityDataBase::class.java,
                        "pokemon_abilities_db"
                    )
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}