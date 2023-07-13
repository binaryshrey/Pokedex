package dev.shreyansh.pokemon.pokedex.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentSearchPokemonBinding
import dev.shreyansh.pokemon.pokedex.utils.PokemonRecyclerAdapter
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory

class SearchPokemonFragment : Fragment() {

    private lateinit var binding : FragmentSearchPokemonBinding
    private lateinit var pokemonRecyclerAdapter : PokemonRecyclerAdapter
    private lateinit var staggeredGridLayoutManager : StaggeredGridLayoutManager
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_search_pokemon, container, false)
        binding.viewModel = pokedexViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setupRecyclerView()
        setupObservers()

        return binding.root
    }

    private fun setupObservers() {
//      pokedexViewModel.allPokemonAbilities.observe(viewLifecycleOwner, Observer {
//            it?.let {
//                abilitiesRecyclerAdapter.submitList(it.toMutableList())
//            }
//        })
    }

    private fun setupRecyclerView() {
        staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.searchPokemonRecyclerView.layoutManager = staggeredGridLayoutManager
        pokemonRecyclerAdapter = PokemonRecyclerAdapter(PokemonRecyclerAdapter.OnClickListener {
            findNavController().navigate(SearchContainerFragmentDirections.actionSearchContainerFragmentToPokemonDetailFragment(it))
        }, requireActivity())
        binding.searchPokemonRecyclerView.adapter = pokemonRecyclerAdapter
    }


}