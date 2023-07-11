package dev.shreyansh.pokemon.pokedex.ui.pokemon.details

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentBaseStatsBinding
import dev.shreyansh.pokemon.pokedex.domain.Pokemon


class BaseStatsFragment : Fragment() {

    private lateinit var selectedPokemon : Pokemon
    private lateinit var binding : FragmentBaseStatsBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        selectedPokemon = requireArguments().getParcelable("selectedPokemon")!!
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_base_stats, container, false)
        binding.pokemon = selectedPokemon
        binding.lifecycleOwner = viewLifecycleOwner

        animateProgressBar(0, selectedPokemon.hp,1000, binding.hpProgress)
        animateProgressBar(0, selectedPokemon.attack,1000, binding.attackProgress)
        animateProgressBar(0, selectedPokemon.defense,1000, binding.defenseProgress)
        animateProgressBar(0, selectedPokemon.specialAttack,1000, binding.splAttackProgress)
        animateProgressBar(0, selectedPokemon.specialDefense,1000, binding.splDefenseProgress)
        animateProgressBar(0, selectedPokemon.speed,1000, binding.speedProgress)
        animateProgressBar(0, selectedPokemon.total,1000, binding.totalProgress)


        return binding.root
    }

    private fun animateProgressBar(startValue: Int, endValue: Int, duration: Long, progressBar: ProgressBar) {
        val animator = ValueAnimator.ofInt(startValue, endValue)
        animator.addUpdateListener { animation ->
            val progress = animation.animatedValue as Int
            progressBar.progress = progress
        }
        animator.duration = duration
        animator.start()
    }
}