package dev.shreyansh.pokemon.pokedex.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.shreyansh.pokemon.pokedex.network.response.LocationResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val LOCATION_BASE_URL = "https://raw.githubusercontent.com/binaryshrey/Pokedex-APP/main/pokedex-api/data-source/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofitLocation = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(LOCATION_BASE_URL)
    .build()


interface LocationService {

    @GET("locations.json")
    suspend fun getPokeLocations(): List<LocationResponse>

}


object LocationsServiceAPI {
    val locationService: LocationService by lazy {
        retrofitLocation.create(LocationService::class.java)
    }
}