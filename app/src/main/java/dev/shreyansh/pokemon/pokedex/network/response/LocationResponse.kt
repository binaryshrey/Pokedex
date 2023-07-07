package dev.shreyansh.pokemon.pokedex.network.response

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@JsonClass(generateAdapter = true)
@Parcelize
data class LocationResponse(
    val id: Int,
    val name: String,
    val locationURL: String,
    val region : String,
    val pokemonName : String,
    val pokemonImgURL : String,
) : Parcelable