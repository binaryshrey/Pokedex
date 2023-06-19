package dev.shreyansh.pokemon.pokedex.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.shreyansh.pokemon.pokedex.network.*
import dev.shreyansh.pokemon.pokedex.network.response.MovesResponse
import dev.shreyansh.pokemon.pokedex.network.response.PokeNewsRequest
import dev.shreyansh.pokemon.pokedex.network.response.PokemonRequest
import kotlinx.coroutines.launch

class PokedexViewModel(application: Application) : ViewModel(){

    enum class PokeNewsAPIStatus { LOADING, ERROR, DONE }
    enum class PokeMonAPIStatus { LOADING, ERROR, DONE }
    enum class MovesStatus { LOADING, ERROR, DONE }


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


    // poke-mons
    private val _pokeMonsStatus = MutableLiveData<PokeMonAPIStatus>()
    val pokeMonsStatus: LiveData<PokeMonAPIStatus>
        get() = _pokeMonsStatus

    private val _pokeMonsResponse = MutableLiveData<List<PokemonRequest>>()
    val pokeMonsResponse: LiveData<List<PokemonRequest>>
        get() = _pokeMonsResponse


    // moves
    private val _movesAPIStatus = MutableLiveData<MovesStatus>()
    val movesAPIStatus: LiveData<MovesStatus>
        get() = _movesAPIStatus

    private val _movesResponse = MutableLiveData<List<MovesResponse>>()
    val movesResponse: LiveData<List<MovesResponse>>
        get() = _movesResponse

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

    fun getAllPokemon(){
        viewModelScope.launch {
            _pokeMonsStatus.value = PokeMonAPIStatus.LOADING
            try{
                val res = PokedexPokemonServiceAPI.pokedexPokemonService.getAllPokeMons()
                Log.i("PokemonAPI:RES","$res")
                _pokeMonsResponse.value = res
                _pokeMonsStatus.value = PokeMonAPIStatus.DONE
            }
            catch (e: Exception){
                Log.e("PokemonAPI:ERROR","${e.message}")
                _pokeMonsStatus.value = PokeMonAPIStatus.ERROR
            }
        }
    }


    fun getAllMoves(){
        viewModelScope.launch {
            _movesAPIStatus.value = MovesStatus.LOADING
            try{
                val res = MovesServiceAPI.movesService.getPokeMoves()
                Log.i("MovesServiceAPI:RES","$res")
                _movesResponse.value = res
                _movesAPIStatus.value = MovesStatus.DONE
            }
            catch (e: Exception){
                Log.e("MovesServiceAPI:ERROR","${e.message}")
                _movesAPIStatus.value = MovesStatus.ERROR
            }
        }
    }



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