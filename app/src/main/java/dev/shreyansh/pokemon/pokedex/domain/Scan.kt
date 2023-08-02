package dev.shreyansh.pokemon.pokedex.domain

import androidx.annotation.Keep

@Keep
data class Scan(
    val name : String = "",
    val confidence : Float = 0f,
    val index : Int = 0
)