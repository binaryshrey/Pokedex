package dev.shreyansh.pokemon.pokedex.domain

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Ability(
    val id: Int,
    val name: String,
    val effect: String,
    val pokemonName : String,
    val pokemonURL : String,
) : Parcelable