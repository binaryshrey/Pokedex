package dev.shreyansh.pokemon.pokedex.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import dev.shreyansh.pokemon.pokedex.db.pokemon_ability.PokemonAbilityDataBase
import dev.shreyansh.pokemon.pokedex.db.pokemon_fav.PokemonFavDataBase
import dev.shreyansh.pokemon.pokedex.db.pokemon_fav.PokemonFavEntity
import dev.shreyansh.pokemon.pokedex.db.pokemon_item.PokemonItemDataBase
import dev.shreyansh.pokemon.pokedex.db.pokemon_location.PokemonLocationDatabase
import dev.shreyansh.pokemon.pokedex.db.pokemon_moves.PokemonMovesDatabase
import dev.shreyansh.pokemon.pokedex.db.pokemon_news.PokemonNewsDataBase
import dev.shreyansh.pokemon.pokedex.db.pokemon_quiz.PokemonQuizDatabase
import dev.shreyansh.pokemon.pokedex.db.pokemon_response.PokemonResponseDataBase
import dev.shreyansh.pokemon.pokedex.db.pokemon_types.PokemonTypesDatabase
import dev.shreyansh.pokemon.pokedex.domain.*
import dev.shreyansh.pokemon.pokedex.repository.PokedexRepository
import dev.shreyansh.pokemon.pokedex.utils.PokedexDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokedexViewModel(application: Application) : ViewModel(){

    val developerURI: String = "https://play.google.com/store/apps/dev?id=7728992479356728320"
    val privacyPolicyURI: String = "https://pokedex-zenstudio.netlify.app/privacy/"
    val termsAndConditionsURI: String = "https://pokedex-zenstudio.netlify.app/terms/"

    enum class PokeNewsAPIStatus { LOADING, ERROR, DONE }
    enum class PokeMonAPIStatus { LOADING, ERROR, DONE }
    enum class MovesStatus { LOADING, ERROR, DONE }
    enum class AbilitiesStatus { LOADING, ERROR, DONE }
    enum class ItemsStatus { LOADING, ERROR, DONE }
    enum class LocationsStatus { LOADING, ERROR, DONE }
    enum class TypesStatus { LOADING, ERROR, DONE }
    enum class QuizStatus { LOADING, ERROR, DONE }



    private val pokemonResponseDataBase = PokemonResponseDataBase.getInstance(application)
    private val pokemonFavDataBase = PokemonFavDataBase.getInstance(application)
    private val pokemonNewsDataBase = PokemonNewsDataBase.getInstance(application)
    private val pokemonAbilityDataBase = PokemonAbilityDataBase.getInstance(application)
    private val pokemonItemDataBase = PokemonItemDataBase.getInstance(application)
    private val pokemonLocationDatabase = PokemonLocationDatabase.getInstance(application)
    private val pokemonMovesDatabase = PokemonMovesDatabase.getInstance(application)
    private val pokemonTypesDatabase = PokemonTypesDatabase.getInstance(application)
    private val pokemonQuizDatabase = PokemonQuizDatabase.getInstance(application)



    private val repository = PokedexRepository(
        pokemonResponseDataBase,
        pokemonFavDataBase,
        pokemonNewsDataBase,
        pokemonAbilityDataBase,
        pokemonItemDataBase,
        pokemonLocationDatabase,
        pokemonMovesDatabase,
        pokemonTypesDatabase,
        pokemonQuizDatabase)


    val allPokemons = repository.allPokemons
    val allFavPokemons = repository.allFavPokemons
    val allFavPokemonsCount = repository.getFavPokemonCount()
    val allPokemonAbilities = repository.allPokemonAbilities
    val allPokemonItems = repository.allPokemonItems
    val allPokemonLocations = repository.allPokemonLocations
    val allPokemonMoves = repository.allPokemonMoves
    val allPokemonTypes = repository.allPokemonTypes
    val allPokemonQuiz = repository.allPokemonQuiz
    val pokeNewsResponse = repository.allPokemonNews



    var savedSearchPokemon = MutableLiveData<List<Pokemon>>()
    var savedSearchMoves = MutableLiveData<List<Moves>>()
    var savedSearchAbilities = MutableLiveData<List<Ability>>()
    var savedSearchItems = MutableLiveData<List<Item>>()
    var savedSearchLocations = MutableLiveData<List<Location>>()
    var savedSearchTypes = MutableLiveData<List<Type>>()
    var savedSearchNews = MutableLiveData<List<PokemonNews>>()


    private val pokedexDataStore = PokedexDataStore.getInstance(application)
    var appTheme = pokedexDataStore.getAppTheme().asLiveData()
    var quizCoolDown = pokedexDataStore.getQuizCoolDown().asLiveData()
    var level = pokedexDataStore.getLevel().asLiveData()


    //login
    private val _loginComplete = MutableLiveData<Boolean>()
    val loginComplete: LiveData<Boolean>
        get() = _loginComplete


    // poke-news
    private val _pokeNewsStatus = MutableLiveData<PokeNewsAPIStatus>()
    val pokeNewsStatus: LiveData<PokeNewsAPIStatus>
        get() = _pokeNewsStatus


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



    // abilities
    private val _abilitiesAPIStatus = MutableLiveData<AbilitiesStatus>()
    val abilitiesAPIStatus: LiveData<AbilitiesStatus>
        get() = _abilitiesAPIStatus


    // locations
    private val _locationsAPIStatus = MutableLiveData<LocationsStatus>()
    val locationsAPIStatus: LiveData<LocationsStatus>
        get() = _locationsAPIStatus


    // items
    private val _itemsAPIStatus = MutableLiveData<ItemsStatus>()
    val itemsAPIStatus: LiveData<ItemsStatus>
        get() = _itemsAPIStatus


    // types
    private val _typesAPIStatus = MutableLiveData<TypesStatus>()
    val typesAPIStatus: LiveData<TypesStatus>
        get() = _typesAPIStatus


    // quiz
    private val _quizAPIStatus = MutableLiveData<QuizStatus>()
    val quizAPIStatus: LiveData<QuizStatus>
        get() = _quizAPIStatus


    init {
        _loginComplete.value = false
        _pokemonFilter.value = "all"
    }


    fun setAppTheme(theme: String?){
        viewModelScope.launch(Dispatchers.IO) {
            if (theme != null) {
                pokedexDataStore.setAppTheme(theme)
            }
        }
    }

    fun setQuizCoolDown(time: Long){
        viewModelScope.launch(Dispatchers.IO) {
            pokedexDataStore.setQuizCoolDown(time)

        }
    }

    fun setLevel(level: Int){
        viewModelScope.launch(Dispatchers.IO) {
            pokedexDataStore.setLevel(level)

        }
    }


    fun getPokeNews(){
        viewModelScope.launch {
            _pokeNewsStatus.value = PokeNewsAPIStatus.LOADING
            try{
                repository.refreshPokemonNewsAPIResponse()
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
                repository.refreshPokemonMovesAPIResponse()
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
                repository.refreshPokemonAbilitiesAPIResponse()
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
                repository.refreshPokemonItemsAPIResponse()
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
                repository.refreshPokemonLocationsAPIResponse()
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
                repository.refreshPokemonTypesAPIResponse()
                _typesAPIStatus.value = TypesStatus.DONE
            }
            catch (e: Exception){
                Log.e("TypesServiceAPI:ERROR","${e.message}")
                _typesAPIStatus.value = TypesStatus.ERROR
            }
        }
    }



    fun getAllQuiz(){
        viewModelScope.launch {
            _quizAPIStatus.value = QuizStatus.LOADING
            try{
                repository.refreshPokemonQuizAPIResponse()
                _quizAPIStatus.value = QuizStatus.DONE
            }
            catch (e: Exception){
                Log.e("QuizServiceAPI:ERROR","${e.message}")
                _quizAPIStatus.value = QuizStatus.ERROR
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

    fun getPokemonByName(pokemonName : String): LiveData<PokemonFavEntity> {
        return repository.getPokemonByName(pokemonName)
    }



    //search
    fun searchPokemon(query:String): LiveData<List<Pokemon>> {
        return repository.searchPokemon("%${query}%")
    }
    fun searchMoves(query:String): LiveData<List<Moves>> {
        return repository.searchMoves("%${query}%")
    }
    fun searchAbilities(query:String): LiveData<List<Ability>> {
        return repository.searchAbilities("%${query}%")
    }
    fun searchItems(query:String): LiveData<List<Item>> {
        return repository.searchItems("%${query}%")
    }
    fun searchLocations(query:String): LiveData<List<Location>> {
        return repository.searchLocations("%${query}%")
    }
    fun searchTypes(query:String): LiveData<List<Type>> {
        return repository.searchTypes("%${query}%")
    }

    fun searchNews(query:String): LiveData<List<PokemonNews>> {
        return repository.searchNews("%${query}%")
    }

    fun resetSearchFields(){
        savedSearchPokemon.value = mutableListOf<Pokemon>()
        savedSearchMoves.value = mutableListOf<Moves>()
        savedSearchAbilities.value = mutableListOf<Ability>()
        savedSearchItems.value = mutableListOf<Item>()
        savedSearchLocations.value = mutableListOf<Location>()
        savedSearchTypes.value = mutableListOf<Type>()
        savedSearchNews.value = mutableListOf<PokemonNews>()
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