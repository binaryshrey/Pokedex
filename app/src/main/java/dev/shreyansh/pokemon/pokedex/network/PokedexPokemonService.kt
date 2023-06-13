package dev.shreyansh.pokemon.pokedex.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.shreyansh.pokemon.pokedex.network.response.PokeNewsRequest
import dev.shreyansh.pokemon.pokedex.network.response.PokemonRequest
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val POKEMON_BASE_URL = "https://api.pokemon.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofitPokemon = Retrofit.Builder()
    .baseUrl(POKEMON_BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()


interface PokedexPokemonService {

    @GET("pokemons.json")
    suspend fun getAllPokemon(): List<PokemonRequest>

}


object PokedexPokemonServiceAPI {
    val pokedexPokemonService: PokedexPokemonService by lazy {
        retrofitPokemon.create(PokedexPokemonService::class.java)
    }
}
