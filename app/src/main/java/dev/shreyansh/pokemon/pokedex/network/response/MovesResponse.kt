package dev.shreyansh.pokemon.pokedex.network.response

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import dev.shreyansh.pokemon.pokedex.db.pokemon_moves.PokemonMovesEntity
import dev.shreyansh.pokemon.pokedex.domain.LearnedByPokemon
import kotlinx.parcelize.Parcelize


@JsonClass(generateAdapter = true)
@Parcelize
data class MovesResponse(
    val id: Int,
    val name: String,
    val power: Int?,
    val accuracy: Int?,
    val effect: String,
    val learnedByPokemon: List<LearnedByPokemon>,
) : Parcelable


fun List<MovesResponse>.asMovesDatabaseModel(): List<PokemonMovesEntity> {
    return map {
        PokemonMovesEntity(
            id = it.id,
            name = it.name,
            power = it.power,
            accuracy = it.accuracy,
            effect = it.effect,
            learnedByPokemon = it.learnedByPokemon
        )
    }
}