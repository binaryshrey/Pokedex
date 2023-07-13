package dev.shreyansh.pokemon.pokedex.ui.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentItemsBinding
import dev.shreyansh.pokemon.pokedex.databinding.FragmentQuizCooldownBinding
import dev.shreyansh.pokemon.pokedex.utils.ItemsRecyclerAdapter
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory


class QuizCooldownFragment : DialogFragment() {

    private lateinit var binding : FragmentQuizCooldownBinding
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.ChatOptionsStyle)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_quiz_cooldown, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.quizCooldownButton.setOnClickListener { dismiss() }


        return binding.root
    }


}