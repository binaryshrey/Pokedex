package dev.shreyansh.pokemon.pokedex.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.shreyansh.pokemon.pokedex.network.response.PokemonRequest
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val POKEMON_BASE_URL = "https://raw.githubusercontent.com/binaryshrey/Pokedex/main/pokedex-api/data-source/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofitPokemon = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(POKEMON_BASE_URL)
    .build()

interface PokedexPokemonService {

    @GET("pokemons.json")
    suspend fun getAllPokeMons(): List<PokemonRequest>

}


object PokedexPokemonServiceAPI {
    val pokedexPokemonService: PokedexPokemonService by lazy {
        retrofitPokemon.create(PokedexPokemonService::class.java)
    }
}
