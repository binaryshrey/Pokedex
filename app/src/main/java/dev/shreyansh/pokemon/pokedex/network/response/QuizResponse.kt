package dev.shreyansh.pokemon.pokedex.network.response

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import dev.shreyansh.pokemon.pokedex.db.pokemon_quiz.PokemonQuizEntity
import kotlinx.parcelize.Parcelize


@JsonClass(generateAdapter = true)
@Parcelize
data class QuizResponse(
    val id: Int,
    val question: String,
    val options: List<String>,
    val answer : String,
) : Parcelable



fun List<QuizResponse>.asQuizDatabaseModel(): List<PokemonQuizEntity> {
    return map {
        PokemonQuizEntity(
            id = it.id,
            question = it.question,
            options = it.options,
            answer = it.answer
        )
    }
}