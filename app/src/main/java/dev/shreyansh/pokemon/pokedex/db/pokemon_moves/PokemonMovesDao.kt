package dev.shreyansh.pokemon.pokedex.db.pokemon_moves

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface PokemonMovesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg pokemonMovesEntity: PokemonMovesEntity)

    @Query("SELECT * FROM pokemon_moves_table")
    fun getAllPokemonMoves() : LiveData<List<PokemonMovesEntity>>

    @Query("DELETE FROM pokemon_moves_table")
    suspend fun clear()

}