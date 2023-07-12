package dev.shreyansh.pokemon.pokedex.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Quiz(
    val id: Int,
    val question: String,
    val options: List<String>,
    val answer : String,
) : Parcelable