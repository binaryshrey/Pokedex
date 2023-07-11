package dev.shreyansh.pokemon.pokedex.db.pokemon_types

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.shreyansh.pokemon.pokedex.domain.Type


@Entity(tableName = "pokemon_types_table")
data class PokemonTypesEntity(
    @PrimaryKey
    var id: Int = 0,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "moves")
    var moves: String = "",

    @ColumnInfo(name = "pokemonName")
    var pokemonName: String = "",

    @ColumnInfo(name = "pokemonImgURL")
    var pokemonImgURL: String = "",
)


fun List<PokemonTypesEntity>.asTypesDomainModel(): List<Type> {
    return map {
        Type(
            id = it.id,
            name = it.name,
            moves = it.moves,
            pokemonName = it.pokemonName,
            pokemonImgURL = it.pokemonImgURL
        )
    }
}