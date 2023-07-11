package dev.shreyansh.pokemon.pokedex.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import dev.shreyansh.pokemon.pokedex.domain.LearnedByPokemon
import com.google.gson.reflect.TypeToken


class MovesConvertor {
    private val gson = Gson()

    @TypeConverter
    fun fromLearnedByPokemon(learnedByPokemon: List<LearnedByPokemon>): String {
        return gson.toJson(learnedByPokemon)
    }

    @TypeConverter
    fun toLearnedByPokemon(learnedByPokemonJson: String): List<LearnedByPokemon> {
        val learnedByPokemonType = object : TypeToken<List<LearnedByPokemon>>() {}.type
        return gson.fromJson(learnedByPokemonJson, learnedByPokemonType)
    }
}
