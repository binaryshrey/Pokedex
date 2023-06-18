package dev.shreyansh.pokemon.pokedex.ui.pokemon.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.shreyansh.pokemon.pokedex.R


class EvolutionFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_evolution, container, false)
    }


}