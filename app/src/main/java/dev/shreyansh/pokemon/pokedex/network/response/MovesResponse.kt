package dev.shreyansh.pokemon.pokedex.network.response

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@JsonClass(generateAdapter = true)
@Parcelize
data class MovesResponse(
    val id: Int,
    val name: String,
    val power: Int?,
    val accuracy: Int?,
    val effect: String,
    val learnedByPokemon : List<LearnedByPokemon>,
) : Parcelable


@JsonClass(generateAdapter = true)
@Parcelize
data class LearnedByPokemon(
    val name: String,
    val url: String,
) : Parcelable