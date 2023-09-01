package dev.shreyansh.pokemon.pokedex.ui.menu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentAccountDeletionBinding
import dev.shreyansh.pokemon.pokedex.utils.NetworkConnection
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory

@AndroidEntryPoint
class AccountDeletionFragment : DialogFragment() {

    private var isConnected = false
    private lateinit var networkConnection: NetworkConnection
    private lateinit var binding : FragmentAccountDeletionBinding
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.ChatOptionsStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_account_deletion, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        setupObservers()
        setupOnClickListeners()

        return binding.root
    }

    private fun setupOnClickListeners() {
        binding.cancelDelButton.setOnClickListener { dismiss() }
        binding.continueDelButton.setOnClickListener { if(isConnected){delAccountAndUserData()} }
    }

    private fun setupObservers() {
        networkConnection = NetworkConnection(requireNotNull(this.activity).application)
        networkConnection.observe(viewLifecycleOwner, Observer { connected ->
            isConnected = connected
        })
    }

    private fun delAccountAndUserData() {
        pokedexViewModel.setLevel(0)
        logOut()
    }

    private fun logOut(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googlesSignInClient = GoogleSignIn.getClient(requireContext(),gso)
        googlesSignInClient.signOut()
        FirebaseAuth.getInstance().signOut()
        findNavController().navigate(AccountDeletionFragmentDirections.actionAccountDeletionFragmentToLoginFragment())
        Log.d("AccountDeletion", "Log Out successful!")
    }
}