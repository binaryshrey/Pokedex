package dev.shreyansh.pokemon.pokedex.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.shreyansh.pokemon.pokedex.network.response.MovesResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET



private const val ABILITIES_BASE_URL = "https://raw.githubusercontent.com/binaryshrey/Pokedex/feat/abilities/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofitAbilities = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(ABILITIES_BASE_URL)
    .build()


interface AbilitiesService {

    @GET("abilities.json")
    suspend fun getPokeMoves(): List<MovesResponse>

}


object AbilitiesServiceAPI {
    val abilitiesService: AbilitiesService by lazy {
        retrofitAbilities.create(AbilitiesService::class.java)
    }
}