package dev.shreyansh.pokemon.pokedex.network.response

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class PokemonRequest(
    val name: String,
    val id: String,
    val height: String,
    val category : String,
    val weight : String,
    val weaknesses: List<String>,
    val evolutions : List<String>,
    val abilities : List<String>,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val speed : Int,
    val total : Int,
    val cycles: String,
    val reason : String,
    @Json(name = "imageurl") val imageUrl: String,
    @Json(name = "base_exp") val baseExp : String,
    @Json(name = "egg_groups") val eggGroups: String,
    @Json(name = "evolvedfrom") val evolvedFrom: String,
    @Json(name = "xdescription") val description: String,
    @Json(name = "typeofpokemon") val type: List<String>,
    @Json(name = "special_attack") val specialAttack: Int,
    @Json(name = "special_defense") val specialDefense: Int,
) : Parcelable