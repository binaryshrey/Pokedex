package dev.shreyansh.pokemon.pokedex.domain

import android.os.Parcelable
import dev.shreyansh.pokemon.pokedex.db.pokemon_news.PokemonNewsEntity
import dev.shreyansh.pokemon.pokedex.network.response.PokeNewsResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ability(
    val id: Int,
    val name: String,
    val effect: String,
    val pokemonName : String,
    val pokemonURL : String,
) : Parcelable