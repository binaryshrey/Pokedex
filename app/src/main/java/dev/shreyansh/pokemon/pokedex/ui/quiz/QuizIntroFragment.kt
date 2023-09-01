package dev.shreyansh.pokemon.pokedex.ui.quiz

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
import dev.shreyansh.pokemon.pokedex.databinding.FragmentQuizIntroBinding
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory
@AndroidEntryPoint
class QuizIntroFragment : Fragment() {

    private var coolDown : Boolean = false
    private lateinit var binding : FragmentQuizIntroBinding
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_quiz_intro, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        setStatusBarColor()
        setupObservers()
        setupOnClickListeners()

        return binding.root
    }

    private fun setStatusBarColor(){
        when(requireContext().resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)){
            Configuration.UI_MODE_NIGHT_YES -> requireActivity().window.statusBarColor = Color.parseColor("#000000")
            Configuration.UI_MODE_NIGHT_NO -> requireActivity().window.statusBarColor = Color.parseColor("#fcf4eb")
            else -> requireActivity().window.statusBarColor = Color.parseColor("#fcf4eb")
        }
    }
    private fun setupObservers() {
        pokedexViewModel.quizCoolDown.observe(viewLifecycleOwner, Observer {
            coolDown = System.currentTimeMillis() <= it
        })
    }

    private fun setupOnClickListeners() {
        binding.quizIntroToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.startQuizButton.setOnClickListener {
            if(coolDown){
                findNavController().navigate(QuizIntroFragmentDirections.actionQuizIntroFragmentToQuizCooldownFragment())
            }else{
                findNavController().navigate(QuizIntroFragmentDirections.actionQuizIntroFragmentToQuizFragment())
            }
        }
    }


}