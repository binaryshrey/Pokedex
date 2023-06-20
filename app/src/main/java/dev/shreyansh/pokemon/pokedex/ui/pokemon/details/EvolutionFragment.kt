package dev.shreyansh.pokemon.pokedex.ui.pokemon.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentEvolutionBinding
import dev.shreyansh.pokemon.pokedex.network.response.PokemonRequest
import dev.shreyansh.pokemon.pokedex.utils.searchPokemonById
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory


class EvolutionFragment : Fragment() {

    private lateinit var selectedPokemon : PokemonRequest
    private lateinit var binding : FragmentEvolutionBinding
    private lateinit var pokeMons: MutableList<PokemonRequest>
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        selectedPokemon = requireArguments().getParcelable("selectedPokemon")!!
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_evolution, container, false)
        binding.pokemon = selectedPokemon
        binding.lifecycleOwner = viewLifecycleOwner

        pokedexViewModel.pokeMonsResponse.observe(viewLifecycleOwner, Observer {
            it?.let {
                pokeMons = it.toMutableList()
                if(pokeMons!=null){
                    setEvolutions()
                }
            }
        })


        return binding.root
    }

    private fun setEvolutions() {
        Log.i("setEvolutions","${selectedPokemon.evolutions.size}")
        when(selectedPokemon.evolutions.size){
            2 -> evolvesOnce()
            3 -> evolvesTwice()
            4 -> evolvesThrice()
            else -> handleEvolutions()
        }
    }

    private fun handleEvolutions() {
        binding.evoLayout.visibility = View.GONE
        binding.emptyEvo.visibility = View.VISIBLE

    }

    private fun evolvesThrice() {
        Glide.with(requireActivity().applicationContext).load("https://assets.pokemon.com/assets/cms2/img/pokedex/full/${selectedPokemon.evolutions[0].replace("#", "")}.png").into(binding.evolve11)
        Glide.with(requireActivity().applicationContext).load("https://assets.pokemon.com/assets/cms2/img/pokedex/full/${selectedPokemon.evolutions[1].replace("#", "")}.png").into(binding.evolve12)
        Glide.with(requireActivity().applicationContext).load("https://assets.pokemon.com/assets/cms2/img/pokedex/full/${selectedPokemon.evolutions[1].replace("#", "")}.png").into(binding.evolve21)
        Glide.with(requireActivity().applicationContext).load("https://assets.pokemon.com/assets/cms2/img/pokedex/full/${selectedPokemon.evolutions[2].replace("#", "")}.png").into(binding.evolve22)
        Glide.with(requireActivity().applicationContext).load("https://assets.pokemon.com/assets/cms2/img/pokedex/full/${selectedPokemon.evolutions[2].replace("#", "")}.png").into(binding.evolve31)
        Glide.with(requireActivity().applicationContext).load("https://assets.pokemon.com/assets/cms2/img/pokedex/full/${selectedPokemon.evolutions[3].replace("#", "")}.png").into(binding.evolve32)



        binding.pokemonEvoTV11.text = searchPokemonById(pokeMons,selectedPokemon.evolutions[0])?.name ?: ""
        binding.pokemonEvoTV12.text = searchPokemonById(pokeMons,selectedPokemon.evolutions[1])?.name ?: ""
        binding.pokemonEvoTV21.text = searchPokemonById(pokeMons,selectedPokemon.evolutions[1])?.name ?: ""
        binding.pokemonEvoTV22.text = searchPokemonById(pokeMons,selectedPokemon.evolutions[2])?.name ?: ""
        binding.pokemonEvoTV31.text = searchPokemonById(pokeMons,selectedPokemon.evolutions[2])?.name ?: ""
        binding.pokemonEvoTV32.text = searchPokemonById(pokeMons,selectedPokemon.evolutions[3])?.name ?: ""


    }

    private fun evolvesTwice() {
        binding.evolveLayout3.visibility = View.GONE
        binding.divider2.visibility = View.GONE


        Glide.with(requireActivity().applicationContext).load("https://assets.pokemon.com/assets/cms2/img/pokedex/full/${selectedPokemon.evolutions[0].replace("#", "")}.png").into(binding.evolve11)
        Glide.with(requireActivity().applicationContext).load("https://assets.pokemon.com/assets/cms2/img/pokedex/full/${selectedPokemon.evolutions[1].replace("#", "")}.png").into(binding.evolve12)
        Glide.with(requireActivity().applicationContext).load("https://assets.pokemon.com/assets/cms2/img/pokedex/full/${selectedPokemon.evolutions[1].replace("#", "")}.png").into(binding.evolve21)
        Glide.with(requireActivity().applicationContext).load("https://assets.pokemon.com/assets/cms2/img/pokedex/full/${selectedPokemon.evolutions[2].replace("#", "")}.png").into(binding.evolve22)

        binding.pokemonEvoTV11.text = searchPokemonById(pokeMons,selectedPokemon.evolutions[0])?.name ?: ""
        binding.pokemonEvoTV12.text = searchPokemonById(pokeMons,selectedPokemon.evolutions[1])?.name ?: ""
        binding.pokemonEvoTV21.text = searchPokemonById(pokeMons,selectedPokemon.evolutions[1])?.name ?: ""
        binding.pokemonEvoTV22.text = searchPokemonById(pokeMons,selectedPokemon.evolutions[2])?.name ?: ""

    }

    private fun evolvesOnce() {
        binding.evolveLayout2.visibility = View.GONE
        binding.evolveLayout3.visibility = View.GONE
        binding.divider1.visibility = View.GONE
        binding.divider2.visibility = View.GONE


        Glide.with(requireActivity().applicationContext).load("https://assets.pokemon.com/assets/cms2/img/pokedex/full/${selectedPokemon.evolutions[0].replace("#", "")}.png").into(binding.evolve11)
        Glide.with(requireActivity().applicationContext).load("https://assets.pokemon.com/assets/cms2/img/pokedex/full/${selectedPokemon.evolutions[1].replace("#", "")}.png").into(binding.evolve12)
        binding.pokemonEvoTV11.text = searchPokemonById(pokeMons,selectedPokemon.evolutions[0])?.name ?: ""
        binding.pokemonEvoTV12.text = searchPokemonById(pokeMons,selectedPokemon.evolutions[1])?.name ?: ""

    }

}