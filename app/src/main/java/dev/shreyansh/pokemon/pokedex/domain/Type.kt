package dev.shreyansh.pokemon.pokedex.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Type(
    val id: Int,
    val name: String,
    val moves: String,
    val pokemonName : String,
    val pokemonImgURL : String,
) : Parcelable