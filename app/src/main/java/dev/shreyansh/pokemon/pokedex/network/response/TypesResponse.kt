package dev.shreyansh.pokemon.pokedex.network.response

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import dev.shreyansh.pokemon.pokedex.db.pokemon_types.PokemonTypesEntity
import dev.shreyansh.pokemon.pokedex.domain.Type
import kotlinx.parcelize.Parcelize


@JsonClass(generateAdapter = true)
@Parcelize
data class TypesResponse(
    val id: Int,
    val name: String,
    val moves: String,
    val pokemonName: String,
    val pokemonImgURL: String,
) : Parcelable


fun List<TypesResponse>.asTypesDatabaseModel(): List<PokemonTypesEntity> {
    return map {
        PokemonTypesEntity(
            id = it.id,
            name = it.name,
            moves = it.moves,
            pokemonName = it.pokemonName,
            pokemonImgURL = it.pokemonImgURL
        )
    }
}