package dev.shreyansh.pokemon.pokedex.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.shreyansh.pokemon.pokedex.network.PokedexNewsAPI
import dev.shreyansh.pokemon.pokedex.network.PokedexPokemonService
import dev.shreyansh.pokemon.pokedex.network.PokedexPokemonServiceAPI
import dev.shreyansh.pokemon.pokedex.network.response.PokeNewsRequest
import kotlinx.coroutines.launch

class PokedexViewModel(application: Application) : ViewModel(){

    enum class PokeNewsAPIStatus { LOADING, ERROR, DONE }

    //login
    private val _loginComplete = MutableLiveData<Boolean>()
    val loginComplete: LiveData<Boolean>
        get() = _loginComplete


    // poke-news
    private val _pokeNewsStatus = MutableLiveData<PokeNewsAPIStatus>()
    val pokeNewsStatus: LiveData<PokeNewsAPIStatus>
        get() = _pokeNewsStatus

    private val _pokeNewsResponse = MutableLiveData<List<PokeNewsRequest>>()
    val pokeNewsResponse: LiveData<List<PokeNewsRequest>>
        get() = _pokeNewsResponse

    init {
        _loginComplete.value = false
    }


    fun getPokeNews(){
        viewModelScope.launch {
            _pokeNewsStatus.value = PokeNewsAPIStatus.LOADING
            try{
                val res = PokedexNewsAPI.pokedexNewsService.getPokeNews(100)
                Log.i("PokedexNewsAPI:RES","$res")
                _pokeNewsResponse.value = res
                _pokeNewsStatus.value = PokeNewsAPIStatus.DONE
            }
            catch (e: Exception){
                Log.e("PokedexNewsAPI:ERROR","${e.message}")
                _pokeNewsStatus.value = PokeNewsAPIStatus.ERROR
            }
        }
    }

//    fun getAllPokemon(){
//        viewModelScope.launch {
//            //_pokeNewsStatus.value = PokeNewsAPIStatus.LOADING
//            try{
//                val res = PokedexPokemonServiceAPI.pokedexPokemonService.getAllPokemon()
//                Log.i("PokemonAPI:RES","$res")
//                //_pokeNewsResponse.value = res
//                //_pokeNewsStatus.value = PokeNewsAPIStatus.DONE
//            }
//            catch (e: Exception){
//                Log.e("PokemonAPI:ERROR","${e.message}")
//                //_pokeNewsStatus.value = PokeNewsAPIStatus.ERROR
//            }
//        }
//    }



    //login-checked
    fun updateLogin() {
        _loginComplete.value = true
    }
    fun onLoginComplete() {
        _loginComplete.value = false
    }
    fun onLoginCancel() {
        _loginComplete.value = false
    }





}