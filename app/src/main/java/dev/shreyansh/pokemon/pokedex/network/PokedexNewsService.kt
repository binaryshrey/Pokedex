package dev.shreyansh.pokemon.pokedex.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.shreyansh.pokemon.pokedex.network.response.PokeNewsResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val POKE_NEWS_BASE_URL = "https://api.pokemon.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofitPokeNews = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(POKE_NEWS_BASE_URL)
    .build()


interface PokedexNewsService {

    @GET("/us/api/news/")
    suspend fun getPokeNews(@Query("count") count: Int): List<PokeNewsResponse>

}


object PokedexNewsAPI {
    val pokedexNewsService: PokedexNewsService by lazy {
        retrofitPokeNews.create(PokedexNewsService::class.java)
    }
}
