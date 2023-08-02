package dev.shreyansh.pokemon.pokedex.db.pokemon_moves

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.shreyansh.pokemon.pokedex.domain.LearnedByPokemon
import dev.shreyansh.pokemon.pokedex.domain.Moves


@Entity(tableName = "pokemon_moves_table")
data class PokemonMovesEntity(
    @PrimaryKey
    var id: Int = 0,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "power")
    var power: Int? = 0,

    @ColumnInfo(name = "accuracy")
    var accuracy: Int? = 0,

    @ColumnInfo(name = "effect")
    var effect: String = "",

    @ColumnInfo(name = "learnedByPokemon")
    var learnedByPokemon: List<LearnedByPokemon> = mutableListOf()
)


fun List<PokemonMovesEntity>.asMovesDomainModel(): List<Moves> {
    return map {
        Moves(
            id = it.id,
            name = it.name,
            power = it.power,
            accuracy = it.accuracy,
            effect = it.effect,
            learnedByPokemon = it.learnedByPokemon
        )
    }
}