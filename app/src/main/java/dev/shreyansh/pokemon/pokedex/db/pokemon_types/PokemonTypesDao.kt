package dev.shreyansh.pokemon.pokedex.db.pokemon_types

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonTypesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg pokemonTypesEntity: PokemonTypesEntity)

    @Query("SELECT * FROM pokemon_types_table")
    fun getAllPokemonTypes(): LiveData<List<PokemonTypesEntity>>

    @Query("SELECT * FROM pokemon_types_table WHERE name LIKE :key")
    fun getTypesByName(key: String): LiveData<List<PokemonTypesEntity>>

    @Query("DELETE FROM pokemon_types_table")
    suspend fun clear()

}