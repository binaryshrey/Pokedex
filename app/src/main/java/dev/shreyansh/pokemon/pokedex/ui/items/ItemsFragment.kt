package dev.shreyansh.pokemon.pokedex.ui.items

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
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentItemsBinding
import dev.shreyansh.pokemon.pokedex.utils.AbilitiesRecyclerAdapter
import dev.shreyansh.pokemon.pokedex.utils.ItemsRecyclerAdapter
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory


class ItemsFragment : Fragment() {

    private lateinit var binding : FragmentItemsBinding
    private lateinit var itemsRecyclerAdapter: ItemsRecyclerAdapter
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        setStatusBarColor()

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_items, container, false)
        binding.viewModel = pokedexViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        getPokeItems()
        setupAbilitiesRecyclerView()
        setupObservers()
        setupOnClickListeners()

        return binding.root
    }

    private fun setupOnClickListeners() {
        binding.itemsToolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }


    private fun getPokeItems() {
        pokedexViewModel.getAllItems()
    }


    private fun setupAbilitiesRecyclerView() {
        itemsRecyclerAdapter = ItemsRecyclerAdapter(requireActivity())
        binding.itemsRV.adapter = itemsRecyclerAdapter

    }


    private fun setupObservers() {
        pokedexViewModel.itemsResponse.observe(viewLifecycleOwner, Observer {
            it?.let {
                itemsRecyclerAdapter.submitList(it.toMutableList())
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