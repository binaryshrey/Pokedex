package dev.shreyansh.pokemon.pokedex.domain

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Moves(
    val id: Int,
    val name: String,
    val power: Int?,
    val accuracy: Int?,
    val effect: String,
    val learnedByPokemon : List<LearnedByPokemon>,
) : Parcelable


@Parcelize
data class LearnedByPokemon(
    val name: String,
    val url: String,
) : Parcelable


