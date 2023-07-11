package dev.shreyansh.pokemon.pokedex.db.pokemon_fav

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface PokemonFavDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFav(pokemonFavEntity: PokemonFavEntity)

    @Query("SELECT * FROM pokemon_fav_table")
    fun getAllFavPokemon(): LiveData<List<PokemonFavEntity>>

    @Delete
    suspend fun deleteFav(pokemonFavEntity: PokemonFavEntity)

    @Query("SELECT COUNT(*) FROM pokemon_fav_table")
    fun getFavPokemonCount(): LiveData<Int>

    @Query("SELECT * FROM pokemon_fav_table WHERE name= :pokemonName")
    fun getPokemonByName(pokemonName : String): LiveData<PokemonFavEntity>

    @Query("DELETE FROM pokemon_fav_table")
    suspend fun clear()

}