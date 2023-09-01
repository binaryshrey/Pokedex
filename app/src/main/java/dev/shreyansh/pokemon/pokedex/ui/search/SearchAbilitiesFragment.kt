package dev.shreyansh.pokemon.pokedex.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentSearchAbilitiesBinding
import dev.shreyansh.pokemon.pokedex.utils.AbilitiesRecyclerAdapter
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory
@AndroidEntryPoint
class SearchAbilitiesFragment : Fragment() {

    private lateinit var binding : FragmentSearchAbilitiesBinding
    private lateinit var abilitiesRecyclerAdapter: AbilitiesRecyclerAdapter
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_search_abilities, container, false)
        binding.viewModel = pokedexViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setupRecyclerView()
        setupObservers()

        return binding.root
    }

    private fun setupRecyclerView() {
        abilitiesRecyclerAdapter = AbilitiesRecyclerAdapter(requireActivity())
        binding.searchAbilitiesRecyclerView.adapter = abilitiesRecyclerAdapter
    }

    private fun setupObservers() {
        pokedexViewModel.savedSearchAbilities.observe(viewLifecycleOwner, Observer {
            if(it.isNullOrEmpty()){
                binding.emptyAbilitiesResultIV.visibility = View.VISIBLE
            } else {
                binding.emptyAbilitiesResultIV.visibility = View.GONE
                abilitiesRecyclerAdapter.submitList(it.toMutableList())
            }
        })
    }

}