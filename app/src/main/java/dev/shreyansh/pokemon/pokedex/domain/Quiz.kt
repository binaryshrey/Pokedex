package dev.shreyansh.pokemon.pokedex.domain

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Quiz(
    val id: Int,
    val question: String,
    val options: List<String>,
    val answer : String,
) : Parcelable