package dev.shreyansh.pokemon.pokedex.db.pokemon_ability

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.shreyansh.pokemon.pokedex.domain.Ability


@Entity(tableName = "pokemon_abilities_table")
data class PokemonAbilityEntity(
    @PrimaryKey
    var id: Int = 0,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "effect")
    var effect: String = "",

    @ColumnInfo(name = "pokemonName")
    var pokemonName : String = "",

    @ColumnInfo(name = "pokemonURL")
    var pokemonURL : String = "",
)


fun List<PokemonAbilityEntity>.asAbilityDomainModel(): List<Ability> {
    return map {
        Ability(
            id = it.id,
            name = it.name,
            effect = it.effect,
            pokemonName = it.pokemonName,
            pokemonURL = it.pokemonURL
        )
    }
}