package dev.shreyansh.pokemon.pokedex.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentSearchMovesBinding
import dev.shreyansh.pokemon.pokedex.utils.MovesRecyclerAdapter
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory


class SearchMovesFragment : Fragment() {

    private lateinit var binding : FragmentSearchMovesBinding
    private lateinit var movesRecyclerAdapter: MovesRecyclerAdapter
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_search_moves, container, false)
        binding.viewModel = pokedexViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setupRecyclerView()
        setupObservers()

        return binding.root
    }

    private fun setupObservers() {
//        pokedexViewModel.allPokemonMoves.observe(viewLifecycleOwner, Observer {allMoves ->
//            allMoves?.let {
//                movesRecyclerAdapter.submitList(allMoves.toMutableList())
//            }
//        })
    }

    private fun setupRecyclerView() {
        movesRecyclerAdapter = MovesRecyclerAdapter(requireActivity())
        binding.searchMovesRecyclerView.adapter = movesRecyclerAdapter
    }


}