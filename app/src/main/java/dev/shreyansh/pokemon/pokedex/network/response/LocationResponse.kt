package dev.shreyansh.pokemon.pokedex.network.response

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import dev.shreyansh.pokemon.pokedex.db.pokemon_location.PokemonLocationEntity
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



fun List<LocationResponse>.asLocationDatabaseModel(): List<PokemonLocationEntity> {
    return map {
        PokemonLocationEntity(
            id = it.id,
            name = it.name,
            locationURL = it.locationURL,
            region = it.region,
            pokemonName = it.pokemonName,
            pokemonImgURL = it.pokemonImgURL
        )
    }
}