package dev.shreyansh.pokemon.pokedex.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonNews(
    val id: Int,
    val title: String,
    val shortDescription: String,
    val image: String,
    val date: String,
    val tags : String,
    val url : String
) : Parcelable