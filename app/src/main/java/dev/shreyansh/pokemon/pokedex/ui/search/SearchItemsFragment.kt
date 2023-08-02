package dev.shreyansh.pokemon.pokedex.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentSearchItemsBinding
import dev.shreyansh.pokemon.pokedex.utils.ItemsRecyclerAdapter
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory


class SearchItemsFragment : Fragment() {

    private lateinit var binding : FragmentSearchItemsBinding
    private lateinit var itemsRecyclerAdapter: ItemsRecyclerAdapter
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_search_items, container, false)
        binding.viewModel = pokedexViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setupRecyclerView()
        setupObservers()

        return binding.root
    }

    private fun setupObservers() {
        pokedexViewModel.savedSearchItems.observe(viewLifecycleOwner, Observer {
            if(it.isNullOrEmpty()){
                binding.emptyItemsResultIV.visibility = View.VISIBLE
            } else {
                binding.emptyItemsResultIV.visibility = View.GONE
                itemsRecyclerAdapter.submitList(it.toMutableList())
            }
        })
    }

    private fun setupRecyclerView() {
        itemsRecyclerAdapter = ItemsRecyclerAdapter(requireActivity())
        binding.searchItemsRecyclerView.adapter = itemsRecyclerAdapter

    }


}