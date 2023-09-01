package dev.shreyansh.pokemon.pokedex.ui.locations

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentLocationsBinding
import dev.shreyansh.pokemon.pokedex.utils.LocationsRecyclerAdapter
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory
@AndroidEntryPoint
class LocationsFragment : Fragment() {

    private lateinit var binding : FragmentLocationsBinding
    private lateinit var locationsRecyclerAdapter: LocationsRecyclerAdapter
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        setStatusBarColor()

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_locations, container, false)
        binding.viewModel = pokedexViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setupLocationsRecyclerView()
        setupObservers()
        setupOnClickListeners()

        return binding.root
    }

    private fun setupOnClickListeners() {
        binding.locationsToolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }


    private fun getPokeLocations() {
        pokedexViewModel.getAllLocations()
    }


    private fun setupLocationsRecyclerView() {
        locationsRecyclerAdapter = LocationsRecyclerAdapter(LocationsRecyclerAdapter.OnClickListener{},requireActivity())
        binding.locationsRV.adapter = locationsRecyclerAdapter

    }

    private fun setupObservers() {
        pokedexViewModel.allPokemonLocations.observe(viewLifecycleOwner, Observer {
            if(!it.isNullOrEmpty()) {
                locationsRecyclerAdapter.submitList(it.toMutableList())
            }else{
                getPokeLocations()
            }
        })
    }


    private fun setStatusBarColor() {
        when(requireContext().resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)){
            Configuration.UI_MODE_NIGHT_YES -> requireActivity().window.statusBarColor = Color.parseColor("#000000")
            Configuration.UI_MODE_NIGHT_NO -> requireActivity().window.statusBarColor = Color.parseColor("#ffffff")
            else -> requireActivity().window.statusBarColor = Color.parseColor("#000000")
        }
    }
}