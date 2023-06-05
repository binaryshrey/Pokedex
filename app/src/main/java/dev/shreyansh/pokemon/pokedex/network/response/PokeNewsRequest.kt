package dev.shreyansh.pokemon.pokedex.network.response

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@JsonClass(generateAdapter = true)
@Parcelize
data class PokeNewsRequest(
    val id: Int,
    val title: String,
    val shortDescription: String,
    val image: String,
    val date: String,
) : Parcelable