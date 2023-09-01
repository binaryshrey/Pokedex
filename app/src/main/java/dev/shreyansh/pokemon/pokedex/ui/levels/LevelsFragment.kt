package dev.shreyansh.pokemon.pokedex.ui.levels

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
import dev.shreyansh.pokemon.pokedex.databinding.FragmentLevelsBinding
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory

@AndroidEntryPoint
class LevelsFragment : Fragment() {


    private lateinit var binding : FragmentLevelsBinding
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_levels, container, false)

        setTitleBarColor()
        setObservers()
        setupOnClickListeners()

        return binding.root
    }

    private fun setObservers() {
        pokedexViewModel.level.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.points.text = "Your Pokemon Trainer Level Points : ${it}"
                disableLayoutVisibility()
                updateLayout(it)
            }
        })
    }




    private fun setupOnClickListeners() {
        binding.levelsToolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    private fun setTitleBarColor() {
        when(requireContext().resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)){
            Configuration.UI_MODE_NIGHT_YES -> requireActivity().window.statusBarColor = Color.parseColor("#000000")
            Configuration.UI_MODE_NIGHT_NO -> requireActivity().window.statusBarColor = Color.parseColor("#ffffff")
            else -> requireActivity().window.statusBarColor = Color.parseColor("#000000")
        }
    }

    private fun updateLayout(level: Int) {
        when (level) {
            0 -> {
                binding.userAppLevelStatus.text = "Beginner"
                binding.beginnerLevel.visibility = View.VISIBLE
            }
            in 1..9 -> {
                binding.userAppLevelStatus.text = "Learner"
                binding.learnerLevel.visibility = View.VISIBLE
            }
            in 10..24 -> {
                binding.userAppLevelStatus.text = "Seeker"
                binding.seekerLevel.visibility = View.VISIBLE
            }
            in 25..49 -> {
                binding.userAppLevelStatus.text = "Explorer"
                binding.explorerLevel.visibility = View.VISIBLE
            }
            in 50..99 -> {
                binding.userAppLevelStatus.text = "Scholar"
                binding.scholarLevel.visibility = View.VISIBLE
            }
            in 100..249 -> {
                binding.userAppLevelStatus.text = "Savant"
                binding.scholarLevel.visibility = View.VISIBLE
            }
            else -> {
                binding.userAppLevelStatus.text = "Sage"
                binding.sageLevel.visibility = View.VISIBLE
            }
        }
    }

    private fun disableLayoutVisibility(){
        binding.beginnerLevel.visibility = View.GONE
        binding.learnerLevel.visibility = View.GONE
        binding.seekerLevel.visibility = View.GONE
        binding.explorerLevel.visibility = View.GONE
        binding.scholarLevel.visibility = View.GONE
        binding.savantLevel.visibility = View.GONE
        binding.sageLevel.visibility = View.GONE

    }


}