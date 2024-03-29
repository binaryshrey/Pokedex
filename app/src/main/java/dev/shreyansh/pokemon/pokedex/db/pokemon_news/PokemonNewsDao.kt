package dev.shreyansh.pokemon.pokedex.db.pokemon_news

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.shreyansh.pokemon.pokedex.db.pokemon_moves.PokemonMovesEntity

@Dao
interface PokemonNewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg pokemonNewsEntity: PokemonNewsEntity)

    @Query("SELECT * FROM pokemon_news_table")
    fun getAllPokemons() : LiveData<List<PokemonNewsEntity>>

    @Query("SELECT * FROM pokemon_news_table WHERE title LIKE :key")
    fun getNewsByTitle(key: String): LiveData<List<PokemonNewsEntity>>

    @Query("DELETE FROM pokemon_news_table")
    suspend fun clear()

}