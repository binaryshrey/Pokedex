package dev.shreyansh.pokemon.pokedex.db.pokemon_response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "pokemon_response_table")
data class PokemonResponseEntity(
    @PrimaryKey
    var id: String = "",

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "height")
    var height: String = "",

    @ColumnInfo(name = "category")
    var category : String = "",

    @ColumnInfo(name = "weight")
    var weight : String = "",

    @ColumnInfo(name = "weaknesses")
    var weaknesses: MutableList<String> = mutableListOf(),

    @ColumnInfo(name = "evolutions")
    var evolutions : MutableList<String> = mutableListOf(),

    @ColumnInfo(name = "abilities")
    var abilities : MutableList<String> = mutableListOf(),

    @ColumnInfo(name = "hp")
    var hp: Int = 0,

    @ColumnInfo(name = "attack")
    var attack: Int = 0,

    @ColumnInfo(name = "defense")
    var defense: Int = 0,

    @ColumnInfo(name = "speed")
    var speed : Int = 0,

    @ColumnInfo(name = "total")
    var total : Int = 0,

    @ColumnInfo(name = "cycles")
    var cycles: String = "",

    @ColumnInfo(name = "reason")
    var reason : String = "",

    @ColumnInfo(name = "imageUrl")
    var imageUrl: String = "",

    @ColumnInfo(name = "baseExp")
    var baseExp : String = "",

    @ColumnInfo(name = "eggGroups")
    var eggGroups: String = "",

    @ColumnInfo(name = "evolvedFrom")
    var evolvedFrom: String = "",

    @ColumnInfo(name = "description")
    var description: String = "",

    @ColumnInfo(name = "type")
    var type: MutableList<String> = mutableListOf(),

    @ColumnInfo(name = "specialAttack")
    var specialAttack: Int = 0,

    @ColumnInfo(name = "specialDefense")
    var specialDefense: Int = 0,

    @ColumnInfo(name = "male")
    var male: String = "",

    @ColumnInfo(name = "female")
    var female: String = "",
)