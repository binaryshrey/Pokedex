package dev.shreyansh.pokemon.pokedex.db.pokemon_response

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface PokemonResponseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg pokemonResponseEntity: PokemonResponseEntity)

    @Query("SELECT * FROM pokemon_response_table")
    fun getAllPokemons() : LiveData<List<PokemonResponseEntity>>

    @Query("SELECT * FROM pokemon_response_table WHERE name LIKE :key")
    fun getPokemonByName(key: String): LiveData<List<PokemonResponseEntity>>

    @Query("DELETE FROM pokemon_response_table")
    suspend fun clear()

}