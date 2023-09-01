package dev.shreyansh.pokemon.pokedex.ui.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.parseAsHtml
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentItemsBinding
import dev.shreyansh.pokemon.pokedex.databinding.FragmentQuizCooldownBinding
import dev.shreyansh.pokemon.pokedex.utils.ItemsRecyclerAdapter
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory

@AndroidEntryPoint
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


        setupObservers()
        setupOnClickListeners()


        return binding.root
    }

    private fun setupObservers() {
        pokedexViewModel.quizCoolDown.observe(viewLifecycleOwner, Observer {
            val remainingMillis = it - System.currentTimeMillis()
            val remainingHours = remainingMillis / (1000 * 60 * 60)
            val remainingMinutes = (remainingMillis % (1000 * 60 * 60)) / (1000 * 60)

            binding.quizCooldownDescTV.text = "<b>Poké-Quiz</b> can be played daily after an <b>interval of 24 hours</b> to increase your pokemon trainer level. Come back again in <b>${remainingHours} hrs - ${remainingMinutes} mins</b> for some more action!".parseAsHtml()
        })
    }

    private fun setupOnClickListeners() {
        binding.quizCooldownButton.setOnClickListener { dismiss() }
    }


}