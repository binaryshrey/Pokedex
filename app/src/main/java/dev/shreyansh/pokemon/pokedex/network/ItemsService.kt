package dev.shreyansh.pokemon.pokedex.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.shreyansh.pokemon.pokedex.network.response.ItemsResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val ITEMS_BASE_URL = "https://raw.githubusercontent.com/binaryshrey/Pokedex/feat/items/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofitItems = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(ITEMS_BASE_URL)
    .build()


interface ItemsService {

    @GET("items.json")
    suspend fun getPokeItems(): List<ItemsResponse>

}


object ItemsServiceAPI {
    val itemsService: ItemsService by lazy {
        retrofitItems.create(ItemsService::class.java)
    }
}