package dev.shreyansh.pokemon.pokedex.network.response

import android.os.Parcelable
import androidx.annotation.Keep
import com.squareup.moshi.JsonClass
import dev.shreyansh.pokemon.pokedex.db.pokemon_ability.PokemonAbilityEntity
import kotlinx.parcelize.Parcelize

@Keep
@JsonClass(generateAdapter = true)
@Parcelize
data class AbilitiesResponse(
    val id: Int,
    val name: String,
    val effect: String,
    val pokemonName : String,
    val pokemonURL : String,
) : Parcelable


fun List<AbilitiesResponse>.asAbilityDatabaseModel(): List<PokemonAbilityEntity> {
    return map {
        PokemonAbilityEntity(
            id = it.id,
            name = it.name,
            effect = it.effect,
            pokemonName = it.pokemonName,
            pokemonURL = it.pokemonURL
        )
    }
}
