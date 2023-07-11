package dev.shreyansh.pokemon.pokedex.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.shreyansh.pokemon.pokedex.db.pokemon_fav.PokemonFavDataBase
import dev.shreyansh.pokemon.pokedex.db.pokemon_fav.PokemonFavEntity
import dev.shreyansh.pokemon.pokedex.db.pokemon_response.PokemonResponseDataBase
import dev.shreyansh.pokemon.pokedex.domain.Pokemon
import dev.shreyansh.pokemon.pokedex.network.*
import dev.shreyansh.pokemon.pokedex.network.response.*
import dev.shreyansh.pokemon.pokedex.repository.PokedexRepository
import kotlinx.coroutines.launch

class PokedexViewModel(application: Application) : ViewModel(){

    enum class PokeNewsAPIStatus { LOADING, ERROR, DONE }
    enum class PokeMonAPIStatus { LOADING, ERROR, DONE }
    enum class MovesStatus { LOADING, ERROR, DONE }
    enum class AbilitiesStatus { LOADING, ERROR, DONE }
    enum class ItemsStatus { LOADING, ERROR, DONE }
    enum class LocationsStatus { LOADING, ERROR, DONE }

    enum class TypesStatus { LOADING, ERROR, DONE }



    private val pokemonResponseDataBase = PokemonResponseDataBase.getInstance(application)
    private val pokemonFavDataBase = PokemonFavDataBase.getInstance(application)

    private val repository = PokedexRepository(pokemonResponseDataBase,pokemonFavDataBase)

    val allPokemons = repository.allPokemons
    val allFavPokemons = repository.allFavPokemons
    val allFavPokemonsCount = repository.getFavPokemonCount()


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

    private val _pokemonFilter = MutableLiveData<String>()
    val pokemonFilter: LiveData<String>
        get() = _pokemonFilter


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
        _pokemonFilter.value = "all"
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
                repository.refreshPokemonAPIResponse()
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


    fun saveFavPokemon(favPokemon: Pokemon){
        val favEntity = PokemonFavEntity(
            id = favPokemon.id,
            name = favPokemon.name,
            height = favPokemon.height,
            category = favPokemon.category,
            weight = favPokemon.weight,
            weaknesses = favPokemon.weaknesses,
            evolutions = favPokemon.evolutions,
            abilities = favPokemon.abilities,
            hp = favPokemon.hp,
            attack = favPokemon.attack,
            defense = favPokemon.defense,
            speed = favPokemon.speed,
            total = favPokemon.total,
            cycles = favPokemon.cycles,
            reason = favPokemon.reason,
            imageUrl = favPokemon.imageUrl,
            baseExp = favPokemon.baseExp,
            eggGroups = favPokemon.eggGroups,
            evolvedFrom = favPokemon.evolvedFrom,
            description = favPokemon.description,
            type = favPokemon.type,
            specialAttack = favPokemon.specialAttack,
            specialDefense = favPokemon.specialDefense,
            male = favPokemon.male,
            female = favPokemon.female
        )
        viewModelScope.launch {
            try{
                repository.insertFavPokemon(favEntity)
            }
            catch (e: Exception){
                Log.e("FavPokemon:ERROR","${e.message}")
            }
        }
    }


    fun removeFavPokemon(favPokemon: Pokemon){
        val favEntity = PokemonFavEntity(
            id = favPokemon.id,
            name = favPokemon.name,
            height = favPokemon.height,
            category = favPokemon.category,
            weight = favPokemon.weight,
            weaknesses = favPokemon.weaknesses,
            evolutions = favPokemon.evolutions,
            abilities = favPokemon.abilities,
            hp = favPokemon.hp,
            attack = favPokemon.attack,
            defense = favPokemon.defense,
            speed = favPokemon.speed,
            total = favPokemon.total,
            cycles = favPokemon.cycles,
            reason = favPokemon.reason,
            imageUrl = favPokemon.imageUrl,
            baseExp = favPokemon.baseExp,
            eggGroups = favPokemon.eggGroups,
            evolvedFrom = favPokemon.evolvedFrom,
            description = favPokemon.description,
            type = favPokemon.type,
            specialAttack = favPokemon.specialAttack,
            specialDefense = favPokemon.specialDefense,
            male = favPokemon.male,
            female = favPokemon.female
        )
        viewModelScope.launch {
            try{
                repository.removeFavPokemon(favEntity)
            }
            catch (e: Exception){
                Log.e("FavPokemon:ERROR","${e.message}")
            }
        }
    }


    fun updatePokemonFilter(filter: String){
        _pokemonFilter.value = filter
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