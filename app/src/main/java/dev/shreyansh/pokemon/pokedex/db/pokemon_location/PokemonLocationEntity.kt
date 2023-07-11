package dev.shreyansh.pokemon.pokedex.db.pokemon_location

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.shreyansh.pokemon.pokedex.domain.Location


@Entity(tableName = "pokemon_locations_table")
data class PokemonLocationEntity(
    @PrimaryKey
    var id: Int = 0,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "locationURL")
    var locationURL: String = "",

    @ColumnInfo(name = "region")
    var region: String = "",

    @ColumnInfo(name = "pokemonName")
    var pokemonName: String = "",

    @ColumnInfo(name = "pokemonImgURL")
    var pokemonImgURL: String = ""
)


fun List<PokemonLocationEntity>.asLocationDomainModel(): List<Location> {
    return map {
        Location(
            id = it.id,
            name = it.name,
            locationURL = it.locationURL,
            region = it.region,
            pokemonName = it.pokemonName,
            pokemonImgURL = it.pokemonImgURL
        )
    }
}