package dev.shreyansh.pokemon.pokedex.db.pokemon_location

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface PokemonLocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg pokemonLocationEntity: PokemonLocationEntity)

    @Query("SELECT * FROM pokemon_locations_table")
    fun getAllPokemonLocations() : LiveData<List<PokemonLocationEntity>>

    @Query("SELECT * FROM pokemon_locations_table WHERE name LIKE :key")
    fun getLocationsByName(key: String): LiveData<List<PokemonLocationEntity>>

    @Query("DELETE FROM pokemon_locations_table")
    suspend fun clear()

}