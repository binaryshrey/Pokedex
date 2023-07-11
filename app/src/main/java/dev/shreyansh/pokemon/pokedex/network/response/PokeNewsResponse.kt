package dev.shreyansh.pokemon.pokedex.network.response

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import dev.shreyansh.pokemon.pokedex.db.pokemon_news.PokemonNewsEntity
import dev.shreyansh.pokemon.pokedex.db.pokemon_response.PokemonResponseEntity
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class PokeNewsResponse(
    val id: Int,
    val title: String,
    val shortDescription: String,
    val image: String,
    val date: String,
    val tags : String,
    val url : String
) : Parcelable


fun List<PokeNewsResponse>.asPokenewsDatabaseModel(): List<PokemonNewsEntity> {
    return map {
        PokemonNewsEntity(
            id = it.id,
            title = it.title,
            shortDescription = it.shortDescription,
            image = it.image,
            date = it.date,
            tags = it.tags,
            url = it.url
        )
    }
}