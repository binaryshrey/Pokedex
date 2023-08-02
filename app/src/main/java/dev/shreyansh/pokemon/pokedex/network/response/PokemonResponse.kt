package dev.shreyansh.pokemon.pokedex.network.response

import android.os.Parcelable
import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.shreyansh.pokemon.pokedex.db.pokemon_response.PokemonResponseEntity
import kotlinx.parcelize.Parcelize
@Keep
@JsonClass(generateAdapter = true)
@Parcelize
data class PokemonResponse(
    val id: String,
    val name: String,
    val height: String,
    val category : String,
    val weight : String,
    val weaknesses: MutableList<String>,
    val evolutions : MutableList<String>,
    val abilities : MutableList<String>,
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
    @Json(name = "typeofpokemon") val type: MutableList<String>,
    @Json(name = "special_attack") val specialAttack: Int,
    @Json(name = "special_defense") val specialDefense: Int,
    @Json(name = "male_percentage") val male: String,
    @Json(name = "female_percentage") val female: String,
) : Parcelable


fun List<PokemonResponse>.asDatabaseModel(): List<PokemonResponseEntity> {
    return map {
        PokemonResponseEntity(
            id = it.id,
            name = it.name,
            height = it.height,
            category = it.category,
            weight = it.weight,
            weaknesses = it.weaknesses,
            evolutions = it.evolutions,
            abilities = it.abilities,
            hp = it.hp,
            attack = it.attack,
            defense = it.defense,
            speed = it.speed,
            total = it.total,
            cycles = it.cycles,
            reason = it.reason,
            imageUrl = it.imageUrl,
            baseExp = it.baseExp,
            eggGroups = it.eggGroups,
            evolvedFrom = it.evolvedFrom,
            description = it.description,
            type = it.type,
            specialAttack = it.specialAttack,
            specialDefense = it.specialDefense,
            male = it.male,
            female = it.female
        )
    }
}