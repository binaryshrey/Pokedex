package dev.shreyansh.pokemon.pokedex.domain

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
@Keep
@Parcelize
data class Item(
    val id: Int,
    val name: String,
    val effect: String,
    val imgURL : String,
) : Parcelable