package dev.shreyansh.pokemon.pokedex.db.pokemon_quiz

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.shreyansh.pokemon.pokedex.domain.Quiz


@Entity(tableName = "pokemon_quiz_table")
data class PokemonQuizEntity(
    @PrimaryKey
    var id: Int = 0,

    @ColumnInfo(name = "question")
    var question: String = "",

    @ColumnInfo(name = "options")
    var options: List<String> = mutableListOf(),

    @ColumnInfo(name = "answer")
    var answer: String = "",
)


fun List<PokemonQuizEntity>.asQuizDomainModel(): List<Quiz> {
    return map {
        Quiz(
            id = it.id,
            question = it.question,
            options = it.options,
            answer = it.answer
        )
    }
}