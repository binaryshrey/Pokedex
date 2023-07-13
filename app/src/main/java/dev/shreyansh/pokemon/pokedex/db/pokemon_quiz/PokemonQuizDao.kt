package dev.shreyansh.pokemon.pokedex.db.pokemon_quiz

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface PokemonQuizDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg pokemonQuizEntity: PokemonQuizEntity)

    @Query("SELECT * FROM pokemon_quiz_table")
    fun getAllPokemonQuiz() : LiveData<List<PokemonQuizEntity>>

    @Query("DELETE FROM pokemon_quiz_table")
    suspend fun clear()

}