package dev.shreyansh.pokemon.pokedex.domain

data class Scan(
    val name : String = "",
    val confidence : Float = 0f,
    val index : Int = 0
)