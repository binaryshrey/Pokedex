package dev.shreyansh.pokemon.pokedex.ui.pokemon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentPokemonDirectoryBinding
import dev.shreyansh.pokemon.pokedex.utils.PokemonRecyclerAdapter
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory

class PokemonDirectoryFragment : Fragment() {

    private var lastVisibleItemPositions: IntArray = IntArray(2)
    private lateinit var binding : FragmentPokemonDirectoryBinding
    private lateinit var pokemonRecyclerAdapter : PokemonRecyclerAdapter
    private lateinit var staggeredGridLayoutManager : StaggeredGridLayoutManager
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_pokemon_directory, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        getAllPokeMons()
        setupPokeMonsRecyclerView()
        setupObservers()
        setupOnClickListeners()


        return binding.root
    }

    private fun setupOnClickListeners() {
        binding.pokeFilter.setOnClickListener {
            findNavController().navigate(PokemonDirectoryContainerFragmentDirections.actionPokemonDirectoryContainerFragmentToPokemonFiltersFragment())
        }
    }

    private fun setupObservers() {
        pokedexViewModel.pokeMonsResponse.observe(viewLifecycleOwner, Observer {
            it?.let {
                pokemonRecyclerAdapter.submitList(it)
            }
        })

        binding.pokemonsRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                staggeredGridLayoutManager.findLastVisibleItemPositions(lastVisibleItemPositions)

                if (dy < 0 && binding.pokeFilter.isShown) {
                    binding.pokeFilter.hide()
                } else if (dy > 0 && !binding.pokeFilter.isShown) {
                    binding.pokeFilter.show()
                }
            }
        })
    }

    private fun getAllPokeMons(){
        pokedexViewModel.getAllPokemon()
    }
    private fun setupPokeMonsRecyclerView() {
        staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.pokemonsRV.layoutManager = staggeredGridLayoutManager
        pokemonRecyclerAdapter = PokemonRecyclerAdapter(PokemonRecyclerAdapter.OnClickListener {
            findNavController().navigate(PokemonDirectoryContainerFragmentDirections.actionPokemonDirectoryContainerFragmentToPokemonDetailFragment(it))
        }, requireActivity())
        binding.pokemonsRV.adapter = pokemonRecyclerAdapter
    }

}