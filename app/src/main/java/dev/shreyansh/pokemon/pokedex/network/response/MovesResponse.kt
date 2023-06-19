package dev.shreyansh.pokemon.pokedex.network.response

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@JsonClass(generateAdapter = true)
@Parcelize
data class MovesResponse(
    val id: Int,
    val name: String,
    val accuracy: Int,
    @Json(name = "effectEntries") val effect_entries: List<Effect>,
    @Json(name = "learnedByPokemon") val learned_by_pokemon: List<PokemonLearned>,
) : Parcelable


@JsonClass(generateAdapter = true)
@Parcelize
data class Effect(
    @Json(name = "effect") val short_effect : String
) : Parcelable


@JsonClass(generateAdapter = true)
@Parcelize
data class PokemonLearned(
    val name : String,
    val url : String,
) : Parcelable