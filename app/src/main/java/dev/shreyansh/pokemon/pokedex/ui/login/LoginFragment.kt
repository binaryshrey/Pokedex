package dev.shreyansh.pokemon.pokedex.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login, container, false)
        initOnClickListeners()

        return binding.root
    }

    private fun initOnClickListeners() {
        binding.privacyTV.setOnClickListener { Toast.makeText(context,"Privacy Policy",Toast.LENGTH_SHORT).show() }
        binding.termsTV.setOnClickListener { Toast.makeText(context,"Terms of Service",Toast.LENGTH_SHORT).show() }

    }


}