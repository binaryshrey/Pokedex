package dev.shreyansh.pokemon.pokedex.ui.abilities

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
import dev.shreyansh.pokemon.pokedex.databinding.FragmentAbilitiesBinding
import dev.shreyansh.pokemon.pokedex.databinding.FragmentMovesBinding
import dev.shreyansh.pokemon.pokedex.utils.AbilitiesRecyclerAdapter
import dev.shreyansh.pokemon.pokedex.utils.MovesRecyclerAdapter
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory


class AbilitiesFragment : Fragment() {
    private lateinit var binding : FragmentAbilitiesBinding
    private lateinit var abilitiesRecyclerAdapter: AbilitiesRecyclerAdapter
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        setStatusBarColor()

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_abilities, container, false)
        binding.viewModel = pokedexViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        getPokeAbilities()
        setupAbilitiesRecyclerView()
        setupObservers()
        setupOnClickListeners()

        return binding.root
    }

    private fun setupOnClickListeners() {
        binding.abilitiesToolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }


    private fun getPokeAbilities() {
        pokedexViewModel.getAllAbilities()
    }


    private fun setupAbilitiesRecyclerView() {
        abilitiesRecyclerAdapter = AbilitiesRecyclerAdapter(requireActivity())
        binding.abilitiesRV.adapter = abilitiesRecyclerAdapter

    }


    private fun setupObservers() {
        pokedexViewModel.abilitiesResponse.observe(viewLifecycleOwner, Observer {
            it?.let {
                abilitiesRecyclerAdapter.submitList(it.toMutableList())
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