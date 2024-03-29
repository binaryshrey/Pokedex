package dev.shreyansh.pokemon.pokedex.utils

import dev.shreyansh.pokemon.pokedex.domain.Pokemon
import dev.shreyansh.pokemon.pokedex.network.response.PokemonResponse
import java.math.RoundingMode
import java.text.DecimalFormat

fun convertHeightToCm(height: String): Double {
    val parts = height.split("'")
    val feet = parts[0].trim().toDouble()
    val inches = parts[1].replace("\"", "").trim().toDouble()
    val totalInches = feet * 12 + inches

    val decimalFormat = DecimalFormat("#.##")
    decimalFormat.roundingMode = RoundingMode.HALF_UP
    return decimalFormat.format(totalInches * 2.54).toDouble()


}

fun convertToKilograms(weight: String): Double {
    val weightPattern = "^(\\d+(\\.\\d+)?)\\s*(lbs|pounds?)$".toRegex(RegexOption.IGNORE_CASE)
    val matchResult = weightPattern.find(weight.trim())

    if (matchResult != null) {
        val weightValue = matchResult.groupValues[1].toDouble()
        val weightUnit = matchResult.groupValues[3].toLowerCase()

        if (weightUnit == "lbs" || weightUnit == "pounds") {
            val kilogramsPerPound = 0.45359237
            val weightInKilograms = weightValue * kilogramsPerPound

            val decimalFormat = DecimalFormat("#.##")
            decimalFormat.roundingMode = RoundingMode.HALF_UP
            return decimalFormat.format(weightInKilograms).toDouble()
        }
    }
    return 0.0
}


fun searchPokemonById(pokemons: List<Pokemon>, idToSearch: String): Pokemon? {
    return pokemons.find { it.id == idToSearch }
}

