package dev.shreyansh.pokemon.pokedex.domain

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
@Keep
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