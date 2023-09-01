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
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentFavPokemonBinding
import dev.shreyansh.pokemon.pokedex.utils.PokemonRecyclerAdapter
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory

@AndroidEntryPoint
class FavPokemonFragment : Fragment() {


    private lateinit var binding : FragmentFavPokemonBinding
    private lateinit var favPokemonRecyclerAdapter : PokemonRecyclerAdapter
    private lateinit var staggeredGridLayoutManager : StaggeredGridLayoutManager
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_fav_pokemon, container, false)
        binding.viewModel = pokedexViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setupFavPokemonRecyclerView()
        setupObservers()

        return binding.root
    }

    private fun setupObservers() {
        pokedexViewModel.allFavPokemons.observe(viewLifecycleOwner, Observer {
            it?.let {
                favPokemonRecyclerAdapter.submitList(it)
            }
        })
    }

    private fun setupFavPokemonRecyclerView() {
        staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.favPokemonsRV.layoutManager = staggeredGridLayoutManager
        favPokemonRecyclerAdapter = PokemonRecyclerAdapter(PokemonRecyclerAdapter.OnClickListener {
            findNavController().navigate(PokemonDirectoryContainerFragmentDirections.actionPokemonDirectoryContainerFragmentToPokemonDetailFragment(it))
        }, requireActivity())
        binding.favPokemonsRV.adapter = favPokemonRecyclerAdapter
    }


}