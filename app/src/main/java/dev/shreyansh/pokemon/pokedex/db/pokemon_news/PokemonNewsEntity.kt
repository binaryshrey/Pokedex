package dev.shreyansh.pokemon.pokedex.db.pokemon_news

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "pokemon_news_table")
data class PokemonNewsEntity(
    @PrimaryKey
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "shortDescription")
    var shortDescription: String = "",

    @ColumnInfo(name = "image")
    var image : String = "",

    @ColumnInfo(name = "date")
    var date : String = "",

    @ColumnInfo(name = "tags")
    var tags : String = "",

    @ColumnInfo(name = "url")
    var url : String = ""
)