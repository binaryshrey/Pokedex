package dev.shreyansh.pokemon.pokedex.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentSearchTypesBinding
import dev.shreyansh.pokemon.pokedex.utils.TypesRecyclerAdapter
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory


class SearchTypesFragment : Fragment() {

    private lateinit var binding : FragmentSearchTypesBinding
    private lateinit var typesRecyclerAdapter: TypesRecyclerAdapter
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_search_types, container, false)
        binding.viewModel = pokedexViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setupRecyclerView()
        setupObservers()

        return binding.root
    }

    private fun setupObservers() {
//        pokedexViewModel.allPokemonTypes.observe(viewLifecycleOwner, Observer {
//            it?.let {
//                typesRecyclerAdapter.submitList(it.toMutableList())
//            }
//        })
    }

    private fun setupRecyclerView() {
        typesRecyclerAdapter = TypesRecyclerAdapter(requireActivity())
        binding.searchTypesRecyclerView.adapter = typesRecyclerAdapter
    }


}