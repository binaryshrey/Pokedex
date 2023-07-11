package dev.shreyansh.pokemon.pokedex.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Location(
    val id: Int,
    val name: String,
    val locationURL: String,
    val region : String,
    val pokemonName : String,
    val pokemonImgURL : String,
) : Parcelable