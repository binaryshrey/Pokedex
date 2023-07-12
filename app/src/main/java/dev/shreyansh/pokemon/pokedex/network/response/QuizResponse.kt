package dev.shreyansh.pokemon.pokedex.network.response

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@JsonClass(generateAdapter = true)
@Parcelize
data class QuizResponse(
    val id: Int,
    val question: String,
    val options: List<String>,
    val answer : String,
) : Parcelable