package dev.shreyansh.pokemon.pokedex.ui.menu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.play.core.review.ReviewManagerFactory
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentAboutBinding
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory


class AboutFragment : Fragment() {

    private lateinit var binding : FragmentAboutBinding
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_about, container, false)

        setupOnClickListeners()

        return binding.root
    }

    private fun setupOnClickListeners() {
        binding.aboutToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.rateAppCV.setOnClickListener {
            rateAppFlow()
        }
        binding.bugCV.setOnClickListener {
            reqNewFeatureBugs()
        }
        binding.devCV.setOnClickListener {
            openWebURI(pokedexViewModel.developerURI)
        }
        binding.privacyPolicy.setOnClickListener {
            openWebURI(pokedexViewModel.privacyPolicyURI)
        }
        binding.terms.setOnClickListener {
            openWebURI(pokedexViewModel.termsAndConditionsURI)
        }
        binding.inviteAppCV.setOnClickListener {
            inviteFriends()
        }

    }


    private fun rateAppFlow() {
        val manager = ReviewManagerFactory.create(requireContext())
        val request = manager.requestReviewFlow()
        request.addOnCompleteListener { request ->
            if (request.isSuccessful) {
                val reviewInfo = request.result
                val flow = manager.launchReviewFlow(requireActivity(), reviewInfo)
                flow.addOnCompleteListener { _ ->
                    Log.i("APP:Rating","Success!")
                }
            } else {
                Log.i("APP:Rating","Failed!")
            }
        }
    }

    private fun openWebURI(url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        startActivity(intent)
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun inviteFriends() : Boolean{
        val message : String = "Hey, checkout this amazing app today: \nPokédex - Gotta explore 'em all \nhttps://play.google.com/store/apps/details?id=dev.shreyansh.pokemon.pokedex"
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, message)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
        return true
    }


    private fun reqNewFeatureBugs() {
        val recipient = "zenstudio.connect@gmail.com"
        val subject = "[New Feature Request]: "
        val message = ""

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, message)
        }

        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }
    }


}