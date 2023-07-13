package dev.shreyansh.pokemon.pokedex.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentSearchLocationsBinding
import dev.shreyansh.pokemon.pokedex.utils.LocationsRecyclerAdapter
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory


class SearchLocationsFragment : Fragment() {

    private lateinit var binding : FragmentSearchLocationsBinding
    private lateinit var locationsRecyclerAdapter: LocationsRecyclerAdapter
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_search_locations, container, false)
        binding.viewModel = pokedexViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setupRecyclerView()
        setupObservers()

        return binding.root
    }

    private fun setupObservers() {
//        pokedexViewModel.allPokemonLocations.observe(viewLifecycleOwner, Observer {
//            it?.let {
//                locationsRecyclerAdapter.submitList(it.toMutableList())
//            }
//        })
    }

    private fun setupRecyclerView() {
        locationsRecyclerAdapter = LocationsRecyclerAdapter(LocationsRecyclerAdapter.OnClickListener{},requireActivity())
        binding.searchLocationsRecyclerView.adapter = locationsRecyclerAdapter
    }


}