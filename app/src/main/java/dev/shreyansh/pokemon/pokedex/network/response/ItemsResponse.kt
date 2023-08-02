package dev.shreyansh.pokemon.pokedex.network.response

import android.os.Parcelable
import androidx.annotation.Keep
import com.squareup.moshi.JsonClass
import dev.shreyansh.pokemon.pokedex.db.pokemon_item.PokemonItemEntity
import kotlinx.parcelize.Parcelize

@Keep
@JsonClass(generateAdapter = true)
@Parcelize
data class ItemsResponse(
    val id: Int,
    val name: String,
    val effect: String,
    val imgURL : String,
) : Parcelable


fun List<ItemsResponse>.asItemDatabaseModel(): List<PokemonItemEntity> {
    return map {
        PokemonItemEntity(
            id = it.id,
            name = it.name,
            effect = it.effect,
            imgURL = it.imgURL
        )
    }
}