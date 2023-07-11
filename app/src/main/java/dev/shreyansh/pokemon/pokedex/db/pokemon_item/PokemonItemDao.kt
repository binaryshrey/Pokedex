package dev.shreyansh.pokemon.pokedex.db.pokemon_item

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface PokemonItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg pokemonItemEntity: PokemonItemEntity)

    @Query("SELECT * FROM pokemon_items_table")
    fun getAllPokemonItems() : LiveData<List<PokemonItemEntity>>

    @Query("DELETE FROM pokemon_items_table")
    suspend fun clear()

}