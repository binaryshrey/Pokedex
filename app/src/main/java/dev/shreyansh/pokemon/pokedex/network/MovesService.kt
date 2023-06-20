package dev.shreyansh.pokemon.pokedex.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.shreyansh.pokemon.pokedex.network.response.MovesResponse
import dev.shreyansh.pokemon.pokedex.network.response.PokeNewsRequest
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query




private const val MOVES_BASE_URL = "https://raw.githubusercontent.com/binaryshrey/Pokedex/main/pokedex-api/data-source/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofitMoves = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(MOVES_BASE_URL)
    .build()


interface MovesService {

    @GET("moves.json")
    suspend fun getPokeMoves(): List<MovesResponse>

}


object MovesServiceAPI {
    val movesService: MovesService by lazy {
        retrofitMoves.create(MovesService::class.java)
    }
}