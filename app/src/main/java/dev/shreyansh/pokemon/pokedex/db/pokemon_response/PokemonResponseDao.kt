package dev.shreyansh.pokemon.pokedex.db.pokemon_response

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy


@Dao
interface PokemonResponseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg pokemonResponseEntity: PokemonResponseEntity)

}