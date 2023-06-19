package dev.shreyansh.pokemon.pokedex.network.response

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@JsonClass(generateAdapter = true)
@Parcelize
data class AbilitiesResponse(
    val id: Int,
    val name: String,
    val effect: String,
    val pokemonName : String,
    val pokemonURL : String,
) : Parcelable
