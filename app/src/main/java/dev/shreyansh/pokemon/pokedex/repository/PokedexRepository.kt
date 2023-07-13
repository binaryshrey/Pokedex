package dev.shreyansh.pokemon.pokedex.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import dev.shreyansh.pokemon.pokedex.db.pokemon_ability.PokemonAbilityDataBase
import dev.shreyansh.pokemon.pokedex.db.pokemon_ability.asAbilityDomainModel
import dev.shreyansh.pokemon.pokedex.db.pokemon_fav.PokemonFavDataBase
import dev.shreyansh.pokemon.pokedex.db.pokemon_fav.PokemonFavEntity
import dev.shreyansh.pokemon.pokedex.db.pokemon_fav.asDomainModel
import dev.shreyansh.pokemon.pokedex.db.pokemon_item.PokemonItemDataBase
import dev.shreyansh.pokemon.pokedex.db.pokemon_item.asItemDomainModel
import dev.shreyansh.pokemon.pokedex.db.pokemon_location.PokemonLocationDatabase
import dev.shreyansh.pokemon.pokedex.db.pokemon_location.asLocationDomainModel
import dev.shreyansh.pokemon.pokedex.db.pokemon_moves.PokemonMovesDatabase
import dev.shreyansh.pokemon.pokedex.db.pokemon_moves.asMovesDomainModel
import dev.shreyansh.pokemon.pokedex.db.pokemon_news.PokemonNewsDataBase
import dev.shreyansh.pokemon.pokedex.db.pokemon_news.asDomainModel
import dev.shreyansh.pokemon.pokedex.db.pokemon_quiz.PokemonQuizDatabase
import dev.shreyansh.pokemon.pokedex.db.pokemon_quiz.asQuizDomainModel
import dev.shreyansh.pokemon.pokedex.db.pokemon_response.PokemonResponseDataBase
import dev.shreyansh.pokemon.pokedex.db.pokemon_response.asDomainModel
import dev.shreyansh.pokemon.pokedex.db.pokemon_types.PokemonTypesDatabase
import dev.shreyansh.pokemon.pokedex.db.pokemon_types.asTypesDomainModel
import dev.shreyansh.pokemon.pokedex.domain.*
import dev.shreyansh.pokemon.pokedex.network.*
import dev.shreyansh.pokemon.pokedex.network.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokedexRepository(
    private val pokemonResponseDataBase: PokemonResponseDataBase,
    private val pokemonFavDataBase: PokemonFavDataBase,
    private val pokemonNewsDataBase: PokemonNewsDataBase,
    private val pokemonAbilityDataBase: PokemonAbilityDataBase,
    private val pokemonItemDataBase: PokemonItemDataBase,
    private val pokemonLocationDatabase: PokemonLocationDatabase,
    private val pokemonMovesDatabase: PokemonMovesDatabase,
    private val pokemonTypesDatabase: PokemonTypesDatabase,
    private val pokemonQuizDatabase: PokemonQuizDatabase

) {

    val allPokemons: LiveData<List<Pokemon>> =
        Transformations.map(pokemonResponseDataBase.pokemonResponseDao.getAllPokemons()) {
            it.asDomainModel()
        }

    val allFavPokemons: LiveData<List<Pokemon>> =
        Transformations.map(pokemonFavDataBase.pokemonFavDao.getAllFavPokemon()) {
            it.asDomainModel()
        }

    val allPokemonNews: LiveData<List<PokemonNews>> =
        Transformations.map(pokemonNewsDataBase.pokemonNewsDao.getAllPokemons()) {
            it.asDomainModel()
        }

    val allPokemonAbilities: LiveData<List<Ability>> =
        Transformations.map(pokemonAbilityDataBase.pokemonAbilityDao.getAllPokemonAbilities()) {
            it.asAbilityDomainModel()
        }

    val allPokemonItems: LiveData<List<Item>> =
        Transformations.map(pokemonItemDataBase.pokemonItemDao.getAllPokemonItems()) {
            it.asItemDomainModel()
        }

    val allPokemonLocations: LiveData<List<Location>> =
        Transformations.map(pokemonLocationDatabase.pokemonLocationDao.getAllPokemonLocations()) {
            it.asLocationDomainModel()
        }

    val allPokemonMoves: LiveData<List<Moves>> =
        Transformations.map(pokemonMovesDatabase.pokemonMovesDao.getAllPokemonMoves()) {
            it.asMovesDomainModel()
        }

    val allPokemonTypes: LiveData<List<Type>> =
        Transformations.map(pokemonTypesDatabase.pokemonTypesDao.getAllPokemonTypes()) {
            it.asTypesDomainModel()
        }

    val allPokemonQuiz: LiveData<List<Quiz>> =
        Transformations.map(pokemonQuizDatabase.pokemonQuizDao.getAllPokemonQuiz()) {
            it.asQuizDomainModel()
        }


    suspend fun refreshPokemonAPIResponse() {
        withContext(Dispatchers.IO) {
            Log.i("Repository:POKEMON-API", "query-pokemon")
            try{
                val res = PokedexPokemonServiceAPI.pokedexPokemonService.getAllPokeMons()
                pokemonResponseDataBase.pokemonResponseDao.insertAll(*res.asDatabaseModel().toTypedArray())
            }
            catch (e: Exception) {
                Log.e("Error:POKEMON-API","${e.toString()}")
            }

        }
    }


    suspend fun refreshPokemonNewsAPIResponse() {
        withContext(Dispatchers.IO) {
            Log.i("Repository:POKENEWS-API", "query-pokenews")
            try{
                val res = PokedexNewsAPI.pokedexNewsService.getPokeNews(150)
                pokemonNewsDataBase.pokemonNewsDao.insertAll(*res.asPokenewsDatabaseModel().toTypedArray())
            }
            catch (e: Exception) {
                Log.e("Error:POKENEWS-API","${e.toString()}")
            }
        }
    }

    suspend fun refreshPokemonAbilitiesAPIResponse() {
        withContext(Dispatchers.IO) {
            Log.i("Repository:Abilities-API", "query-abilities")
            try{
                val res = AbilitiesServiceAPI.abilitiesService.getPokeAbilities()
                pokemonAbilityDataBase.pokemonAbilityDao.insertAll(*res.asAbilityDatabaseModel().toTypedArray())
            }
            catch (e: Exception) {
                Log.e("Error:Abilities-API","${e.toString()}")
            }
        }
    }


    suspend fun refreshPokemonItemsAPIResponse() {
        withContext(Dispatchers.IO) {
            Log.i("Repository:Items-API", "query-items")
            try{
                val res = ItemsServiceAPI.itemsService.getPokeItems()
                pokemonItemDataBase.pokemonItemDao.insertAll(*res.asItemDatabaseModel().toTypedArray())
            }
            catch (e: Exception) {
                Log.e("Error:Items-API","${e.toString()}")
            }
        }
    }


    suspend fun refreshPokemonLocationsAPIResponse() {
        withContext(Dispatchers.IO) {
            Log.i("Repository:Locations-API", "query-locations")
            try{
                val res = LocationsServiceAPI.locationService.getPokeLocations()
                pokemonLocationDatabase.pokemonLocationDao.insertAll(*res.asLocationDatabaseModel().toTypedArray())
            }
            catch (e: Exception) {
                Log.e("Error:Locations-API","${e.toString()}")
            }
        }
    }


    suspend fun refreshPokemonMovesAPIResponse() {
        withContext(Dispatchers.IO) {
            Log.i("Repository:Moves-API", "query-moves")
            try{
                val res = MovesServiceAPI.movesService.getPokeMoves()
                pokemonMovesDatabase.pokemonMovesDao.insertAll(*res.asMovesDatabaseModel().toTypedArray())
            }
            catch (e: Exception) {
                Log.e("Error:Moves-API","${e.toString()}")
            }
        }
    }


    suspend fun refreshPokemonTypesAPIResponse() {
        withContext(Dispatchers.IO) {
            Log.i("Repository:Types-API", "query-types")
            try{
                val res = TypesServiceAPI.typesService.getPokeTypes()
                pokemonTypesDatabase.pokemonTypesDao.insertAll(*res.asTypesDatabaseModel().toTypedArray())
            }
            catch (e: Exception) {
                Log.e("Error:Types-API","${e.toString()}")
            }
        }
    }


    suspend fun refreshPokemonQuizAPIResponse() {
        withContext(Dispatchers.IO) {
            Log.i("Repository:Quiz-API", "query-quiz")
            try{
                val res = QuizServiceAPI.quizService.getPokeQuiz()
                pokemonQuizDatabase.pokemonQuizDao.insertAll(*res.asQuizDatabaseModel().toTypedArray())
            }
            catch (e: Exception) {
                Log.e("Error:Quiz-API","${e.toString()}")
            }
        }
    }



    suspend fun insertFavPokemon(pokemonFavEntity: PokemonFavEntity) {
        pokemonFavDataBase.pokemonFavDao.insertFav(pokemonFavEntity)
    }

    suspend fun removeFavPokemon(pokemonFavEntity: PokemonFavEntity) {
        pokemonFavDataBase.pokemonFavDao.deleteFav(pokemonFavEntity)
    }

    fun getFavPokemonCount() : LiveData<Int> {
        return pokemonFavDataBase.pokemonFavDao.getFavPokemonCount()
    }

    fun getPokemonByName(pokemonName : String) : LiveData<PokemonFavEntity> {
        return pokemonFavDataBase.pokemonFavDao.getPokemonByName(pokemonName)
    }



    //search
    fun searchPokemon(query : String): LiveData<List<Pokemon>> {
        return Transformations.map(pokemonResponseDataBase.pokemonResponseDao.getPokemonByName(query)){
            it.asDomainModel()
        }
    }
    fun searchMoves(query : String): LiveData<List<Moves>> {
        return Transformations.map(pokemonMovesDatabase.pokemonMovesDao.getMovesByName(query)){
            it.asMovesDomainModel()
        }
    }
    fun searchAbilities(query : String): LiveData<List<Ability>> {
        return Transformations.map(pokemonAbilityDataBase.pokemonAbilityDao.getAbilitiesByName(query)){
            it.asAbilityDomainModel()
        }
    }
    fun searchItems(query : String): LiveData<List<Item>> {
        return Transformations.map(pokemonItemDataBase.pokemonItemDao.getItemsByName(query)){
            it.asItemDomainModel()
        }
    }
    fun searchLocations(query : String): LiveData<List<Location>> {
        return Transformations.map(pokemonLocationDatabase.pokemonLocationDao.getLocationsByName(query)){
            it.asLocationDomainModel()
        }
    }
    fun searchTypes(query : String): LiveData<List<Type>> {
        return Transformations.map(pokemonTypesDatabase.pokemonTypesDao.getTypesByName(query)){
            it.asTypesDomainModel()
        }
    }
    fun searchNews(query : String): LiveData<List<PokemonNews>> {
        return Transformations.map(pokemonNewsDataBase.pokemonNewsDao.getNewsByTitle(query)){
            it.asDomainModel()
        }
    }
}