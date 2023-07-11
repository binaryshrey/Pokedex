package dev.shreyansh.pokemon.pokedex.db.pokemon_item

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.shreyansh.pokemon.pokedex.domain.Item


@Entity(tableName = "pokemon_items_table")
data class PokemonItemEntity(
    @PrimaryKey
    var id: Int = 0,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "effect")
    var effect: String = "",

    @ColumnInfo(name = "imgURL")
    var imgURL : String = "",
)


fun List<PokemonItemEntity>.asItemDomainModel(): List<Item> {
    return map {
        Item(
            id = it.id,
            name = it.name,
            effect = it.effect,
            imgURL = it.imgURL
        )
    }
}