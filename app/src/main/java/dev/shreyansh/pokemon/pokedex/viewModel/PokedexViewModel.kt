package dev.shreyansh.pokemon.pokedex.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.shreyansh.pokemon.pokedex.network.*
import dev.shreyansh.pokemon.pokedex.network.response.*
import kotlinx.coroutines.launch

class PokedexViewModel(application: Application) : ViewModel(){

    enum class PokeNewsAPIStatus { LOADING, ERROR, DONE }
    enum class PokeMonAPIStatus { LOADING, ERROR, DONE }
    enum class MovesStatus { LOADING, ERROR, DONE }
    enum class AbilitiesStatus { LOADING, ERROR, DONE }
    enum class ItemsStatus { LOADING, ERROR, DONE }
    enum class LocationsStatus { LOADING, ERROR, DONE }

    enum class TypesStatus { LOADING, ERROR, DONE }



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
    private val _pokemonAPIStatus = MutableLiveData<PokeMonAPIStatus>()
    val pokemonAPIStatus: LiveData<PokeMonAPIStatus>
        get() = _pokemonAPIStatus

    private val _pokeMonsResponse = MutableLiveData<List<PokemonRequest>>()
    val pokeMonsResponse: LiveData<List<PokemonRequest>>
        get() = _pokeMonsResponse

    private val _allPokemons = MutableLiveData<List<PokemonRequest>>()
    val allPokemons: LiveData<List<PokemonRequest>>
        get() = _allPokemons


    // moves
    private val _movesAPIStatus = MutableLiveData<MovesStatus>()
    val movesAPIStatus: LiveData<MovesStatus>
        get() = _movesAPIStatus

    private val _movesResponse = MutableLiveData<List<MovesResponse>>()
    val movesResponse: LiveData<List<MovesResponse>>
        get() = _movesResponse


    // abilities
    private val _abilitiesAPIStatus = MutableLiveData<AbilitiesStatus>()
    val abilitiesAPIStatus: LiveData<AbilitiesStatus>
        get() = _abilitiesAPIStatus

    private val _abilitiesResponse = MutableLiveData<List<AbilitiesResponse>>()
    val abilitiesResponse: LiveData<List<AbilitiesResponse>>
        get() = _abilitiesResponse


    // locations
    private val _locationsAPIStatus = MutableLiveData<LocationsStatus>()
    val locationsAPIStatus: LiveData<LocationsStatus>
        get() = _locationsAPIStatus

    private val _locationsResponse = MutableLiveData<List<LocationResponse>>()
    val locationsResponse: LiveData<List<LocationResponse>>
        get() = _locationsResponse


    // items
    private val _itemsAPIStatus = MutableLiveData<ItemsStatus>()
    val itemsAPIStatus: LiveData<ItemsStatus>
        get() = _itemsAPIStatus

    private val _itemsResponse = MutableLiveData<List<ItemsResponse>>()
    val itemsResponse: LiveData<List<ItemsResponse>>
        get() = _itemsResponse



    // types
    private val _typesAPIStatus = MutableLiveData<TypesStatus>()
    val typesAPIStatus: LiveData<TypesStatus>
        get() = _typesAPIStatus

    private val _typesResponse = MutableLiveData<List<TypesResponse>>()
    val typesResponse: LiveData<List<TypesResponse>>
        get() = _typesResponse


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
            _pokemonAPIStatus.value = PokeMonAPIStatus.LOADING
            try{
                val res = PokedexPokemonServiceAPI.pokedexPokemonService.getAllPokeMons()
                Log.i("PokemonAPI:RES","$res")
                _pokeMonsResponse.value = res
                _allPokemons.value = res
                _pokemonAPIStatus.value = PokeMonAPIStatus.DONE
            }
            catch (e: Exception){
                Log.e("PokemonAPI:ERROR","${e.message}")
                _pokemonAPIStatus.value = PokeMonAPIStatus.ERROR
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


    fun getAllAbilities(){
        viewModelScope.launch {
            _abilitiesAPIStatus.value = AbilitiesStatus.LOADING
            try{
                val res = AbilitiesServiceAPI.abilitiesService.getPokeAbilities()
                Log.i("AbilitiesServiceAPI:RES","$res")
                _abilitiesResponse.value = res
                _abilitiesAPIStatus.value = AbilitiesStatus.DONE
            }
            catch (e: Exception){
                Log.e("AbilitiesServiceAPI:ERROR","${e.message}")
                _abilitiesAPIStatus.value = AbilitiesStatus.ERROR
            }
        }
    }


    fun getAllItems(){
        viewModelScope.launch {
            _itemsAPIStatus.value = ItemsStatus.LOADING
            try{
                val res = ItemsServiceAPI.itemsService.getPokeItems()
                Log.i("ItemsServiceAPI:RES","$res")
                _itemsResponse.value = res
                _itemsAPIStatus.value = ItemsStatus.DONE
            }
            catch (e: Exception){
                Log.e("ItemsServiceAPI:ERROR","${e.message}")
                _itemsAPIStatus.value = ItemsStatus.ERROR
            }
        }
    }


    fun getAllLocations(){
        viewModelScope.launch {
            _locationsAPIStatus.value = LocationsStatus.LOADING
            try{
                val res = LocationsServiceAPI.locationService.getPokeLocations()
                Log.i("LocationsServiceAPI:RES","$res")
                _locationsResponse.value = res
                _locationsAPIStatus.value = LocationsStatus.DONE
            }
            catch (e: Exception){
                Log.e("LocationsServiceAPI:ERROR","${e.message}")
                _locationsAPIStatus.value = LocationsStatus.ERROR
            }
        }
    }

    fun getAllTypes(){
        viewModelScope.launch {
            _typesAPIStatus.value = TypesStatus.LOADING
            try{
                val res = TypesServiceAPI.typesService.getPokeTypes()
                Log.i("TypesServiceAPI:RES","$res")
                _typesResponse.value = res
                _typesAPIStatus.value = TypesStatus.DONE
            }
            catch (e: Exception){
                Log.e("TypesServiceAPI:ERROR","${e.message}")
                _typesAPIStatus.value = TypesStatus.ERROR
            }
        }
    }


    fun filterPokemons(gen : String){
        when(gen){
            "all"-> _pokeMonsResponse.value = _allPokemons.value
            "one" -> _pokeMonsResponse.value = _allPokemons.value?.toMutableList()?.subList(0,150)
            "two" -> _pokeMonsResponse.value = _allPokemons.value?.toMutableList()?.subList(150,250)
            "three" -> _pokeMonsResponse.value = _allPokemons.value?.toMutableList()?.subList(250,386)
            "four" -> _pokeMonsResponse.value = _allPokemons.value?.toMutableList()?.subList(386,493)
            "five" -> _pokeMonsResponse.value = _allPokemons.value?.toMutableList()?.subList(493,649)
            "six" -> _pokeMonsResponse.value = _allPokemons.value?.toMutableList()?.subList(649,721)
            "seven" -> _pokeMonsResponse.value = _allPokemons.value?.toMutableList()?.subList(721,807)
            "eight" -> _pokeMonsResponse.value = _allPokemons.value?.toMutableList()?.subList(807,809)

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