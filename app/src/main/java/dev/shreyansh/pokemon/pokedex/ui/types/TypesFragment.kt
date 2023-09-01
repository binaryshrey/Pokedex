package dev.shreyansh.pokemon.pokedex.ui.types

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
import dev.shreyansh.pokemon.pokedex.databinding.FragmentTypesBinding
import dev.shreyansh.pokemon.pokedex.utils.TypesRecyclerAdapter
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory

@AndroidEntryPoint
class TypesFragment : Fragment() {

    private lateinit var binding : FragmentTypesBinding
    private lateinit var typesRecyclerAdapter: TypesRecyclerAdapter
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setStatusBarColor()

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_types, container, false)
        binding.viewModel = pokedexViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setupTypesRecyclerView()
        setupObservers()
        setupOnClickListeners()

        return binding.root
    }

    private fun setupOnClickListeners() {
        binding.typesToolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }


    private fun getPokeTypes() {
        pokedexViewModel.getAllTypes()
    }


    private fun setupTypesRecyclerView() {
        typesRecyclerAdapter = TypesRecyclerAdapter(requireActivity())
        binding.typesRV.adapter = typesRecyclerAdapter

    }


    private fun setupObservers() {
        pokedexViewModel.allPokemonTypes.observe(viewLifecycleOwner, Observer {
            if(!it.isNullOrEmpty()) {
                typesRecyclerAdapter.submitList(it.toMutableList())
            }else{
                getPokeTypes()
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