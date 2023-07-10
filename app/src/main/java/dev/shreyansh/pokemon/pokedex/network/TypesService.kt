package dev.shreyansh.pokemon.pokedex.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.shreyansh.pokemon.pokedex.network.response.TypesResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val TYPES_BASE_URL = "https://raw.githubusercontent.com/binaryshrey/Pokedex/feat/types/pokedex-api/data-source/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofitItems = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(TYPES_BASE_URL)
    .build()


interface TypesService {

    @GET("types.json")
    suspend fun getPokeTypes(): List<TypesResponse>

}


object TypesServiceAPI {
    val typesService: TypesService by lazy {
        retrofitItems.create(TypesService::class.java)
    }
}