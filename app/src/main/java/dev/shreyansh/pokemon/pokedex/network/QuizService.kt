package dev.shreyansh.pokemon.pokedex.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.shreyansh.pokemon.pokedex.network.response.QuizResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val QUIZ_BASE_URL = "https://raw.githubusercontent.com/binaryshrey/Pokedex/feat/poke-quiz/pokedex-api/data-source/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofitQuiz = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(QUIZ_BASE_URL)
    .build()


interface QuizService {

    @GET("quiz.json")
    suspend fun getPokeQuiz(): List<QuizResponse>

}


object QuizServiceAPI {
    val quizService: QuizService by lazy {
        retrofitQuiz.create(QuizService::class.java)
    }
}