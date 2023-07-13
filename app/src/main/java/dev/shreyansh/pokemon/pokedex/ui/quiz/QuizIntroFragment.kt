package dev.shreyansh.pokemon.pokedex.ui.quiz

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
import dev.shreyansh.pokemon.pokedex.databinding.FragmentQuizIntroBinding
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory

class QuizIntroFragment : Fragment() {

    private var coolDown : Boolean = false
    private lateinit var binding : FragmentQuizIntroBinding
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_quiz_intro, container, false)
        requireActivity().window.statusBarColor = Color.parseColor("#fcf4eb")
        binding.lifecycleOwner = viewLifecycleOwner

        setupObservers()
        setupOnClickListeners()

        return binding.root
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