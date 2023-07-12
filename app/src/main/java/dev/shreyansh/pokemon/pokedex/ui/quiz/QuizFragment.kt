package dev.shreyansh.pokemon.pokedex.ui.quiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentQuizBinding
import dev.shreyansh.pokemon.pokedex.domain.Quiz
import dev.shreyansh.pokemon.pokedex.utils.ItemsRecyclerAdapter
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory


class QuizFragment : Fragment() {

    private lateinit var quizItems : MutableList<Quiz>
    private lateinit var binding : FragmentQuizBinding
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_quiz, container, false)

        binding.viewModel = pokedexViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setupObservers()
        return binding.root
    }

    private fun setupObservers() {
        pokedexViewModel.allPokemonQuiz.observe(viewLifecycleOwner, Observer {
            if(it.isNullOrEmpty()){
                pokedexViewModel.getAllQuiz()
            }else{
                quizItems = it.toMutableList()
                binding.quizNestedScrollView.visibility = View.VISIBLE
            }
        })
    }


}