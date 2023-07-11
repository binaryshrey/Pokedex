package dev.shreyansh.pokemon.pokedex.db.pokemon_ability

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface PokemonAbilityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg pokemonAbilityEntity: PokemonAbilityEntity)

    @Query("SELECT * FROM pokemon_abilities_table")
    fun getAllPokemonAbilities() : LiveData<List<PokemonAbilityEntity>>

    @Query("DELETE FROM pokemon_abilities_table")
    suspend fun clear()

}