package dev.shreyansh.pokemon.pokedex.ui.menu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentMenuBinding
import dev.shreyansh.pokemon.pokedex.utils.NetworkConnection
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory

@AndroidEntryPoint
class MenuFragment : BottomSheetDialogFragment() {

    private lateinit var binding : FragmentMenuBinding
    private var isConnected = false
    private lateinit var networkConnection: NetworkConnection
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.DialogStyle)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_menu, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

       setupObservers()
       setupOnClickListeners()


        return binding.root
    }

    private fun setupOnClickListeners() {
        binding.pokeScanCV.setOnClickListener {
            findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToPokeScanIntroFragment())
        }
        binding.quizMenuCV.setOnClickListener {
            findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToQuizIntroFragment())
        }
        binding.infoCV.setOnClickListener {
            findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToAboutFragment())
        }
        binding.settingsCV.setOnClickListener {
            findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToSettingsFragment())
        }
        binding.logOutCV.setOnClickListener {
            signOutFlow()
        }
    }

    private fun signOutFlow() {
        val logOutDialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle("Confirm Log Out")
            .setMessage("Are you sure you want to Log Out ?")
            .setNegativeButton("No") { _, _ ->
            }
            .setPositiveButton("Yes") { _, _ ->
                logOut()
            }
        logOutDialog.show()
    }

    private fun logOut() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googlesSignInClient = GoogleSignIn.getClient(requireContext(),gso)
        googlesSignInClient.signOut()
        FirebaseAuth.getInstance().signOut()
        findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToLoginFragment())
        Log.d("LogOut", "Log Out successful!")
    }

    private fun setupObservers() {
        networkConnection = NetworkConnection(requireNotNull(this.activity).application)
        networkConnection.observe(viewLifecycleOwner, Observer { connected ->
            isConnected = connected
        })
    }


}