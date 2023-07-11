package dev.shreyansh.pokemon.pokedex.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import dev.shreyansh.pokemon.pokedex.db.pokemon_response.PokemonResponseDataBase
import dev.shreyansh.pokemon.pokedex.db.pokemon_response.asDomainModel
import dev.shreyansh.pokemon.pokedex.domain.Pokemon
import dev.shreyansh.pokemon.pokedex.network.PokedexPokemonServiceAPI
import dev.shreyansh.pokemon.pokedex.network.response.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokedexRepository(private val pokemonResponseDataBase: PokemonResponseDataBase) {

    val allPokemons: LiveData<List<Pokemon>> =
        Transformations.map(pokemonResponseDataBase.pokemonResponseDao.getAllPokemons()) {
            it.asDomainModel()
        }

    suspend fun refreshPokemonAPIResponse() {
        withContext(Dispatchers.IO) {
            Log.i("Repository:POKEMON-API", "query-pokemon")
            try{
                val res = PokedexPokemonServiceAPI.pokedexPokemonService.getAllPokeMons()
                pokemonResponseDataBase.pokemonResponseDao.insertAll(*res.asDatabaseModel().toTypedArray())
            }
            catch (e: Exception) {
                Log.e("Error::POKEMON-API","${e.toString()}")
            }

        }
    }
}