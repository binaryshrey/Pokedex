package dev.shreyansh.pokemon.pokedex.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import dev.shreyansh.pokemon.pokedex.db.pokemon_fav.PokemonFavDataBase
import dev.shreyansh.pokemon.pokedex.db.pokemon_fav.PokemonFavEntity
import dev.shreyansh.pokemon.pokedex.db.pokemon_fav.asDomainModel
import dev.shreyansh.pokemon.pokedex.db.pokemon_response.PokemonResponseDataBase
import dev.shreyansh.pokemon.pokedex.db.pokemon_response.asDomainModel
import dev.shreyansh.pokemon.pokedex.domain.Pokemon
import dev.shreyansh.pokemon.pokedex.network.PokedexPokemonServiceAPI
import dev.shreyansh.pokemon.pokedex.network.response.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokedexRepository(
    private val pokemonResponseDataBase: PokemonResponseDataBase,
    private val pokemonFavDataBase: PokemonFavDataBase
    ) {

    val allPokemons: LiveData<List<Pokemon>> =
        Transformations.map(pokemonResponseDataBase.pokemonResponseDao.getAllPokemons()) {
            it.asDomainModel()
        }

    val allFavPokemons: LiveData<List<Pokemon>> =
        Transformations.map(pokemonFavDataBase.pokemonFavDao.getAllFavPokemon()) {
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

    suspend fun insertFavPokemon(pokemonFavEntity: PokemonFavEntity) {
        pokemonFavDataBase.pokemonFavDao.insertFav(pokemonFavEntity)
    }

    suspend fun removeFavPokemon(pokemonFavEntity: PokemonFavEntity) {
        pokemonFavDataBase.pokemonFavDao.deleteFav(pokemonFavEntity)
    }

    fun getFavPokemonCount() : LiveData<Int> {
        return pokemonFavDataBase.pokemonFavDao.getFavPokemonCount()
    }

    fun getPokemonByName(pokemonName : String) : LiveData<PokemonFavEntity> {
        return pokemonFavDataBase.pokemonFavDao.getPokemonByName(pokemonName)
    }
}