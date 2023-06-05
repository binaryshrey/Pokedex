package dev.shreyansh.pokemon.pokedex.ui.login

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentLoginBinding
import dev.shreyansh.pokemon.pokedex.utils.NetworkConnection
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory


class LoginFragment : Fragment() {


    // init
    private var isConnected = false
    private lateinit var auth: FirebaseAuth
    private lateinit var binding : FragmentLoginBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var networkConnection: NetworkConnection
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }




    // onCreate : override back-press
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }




    // onCreateView
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        setupFireBaseAuth()
        setStatusBarToTransparent()


        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login, container, false)
        binding.viewModel = pokedexViewModel
        binding.lifecycleOwner = viewLifecycleOwner


        setupObservers()
        initOnClickListeners()

        return binding.root
    }




    private fun setupFireBaseAuth(){
        auth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity().applicationContext, gso)
    }



    private fun setStatusBarToTransparent(){
        requireActivity().window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }




    private fun setupObservers() {
        observeNetworkConn()
        observeLoginEvent()
    }


    private fun observeNetworkConn(){
        networkConnection = NetworkConnection(requireNotNull(this.activity).application)
        networkConnection.observe(viewLifecycleOwner, Observer { connected ->
            isConnected = connected
        })
    }


    private fun observeLoginEvent(){
        pokedexViewModel.loginComplete.observe(viewLifecycleOwner, Observer { hasLoggedIn ->
            if (hasLoggedIn) {
                if(isConnected){
                    binding.loginProgress.visibility = View.VISIBLE
                    signInFlow()
                    pokedexViewModel.onLoginComplete()
                }
                else{
                    Toast.makeText(context,"NO INTERNET CONNECTION!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }




    private fun signInFlow() {
        val signInIntent = googleSignInClient.signInIntent
        startForResult.launch(signInIntent)
    }




    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                if (task.isSuccessful) {
                    val account: GoogleSignInAccount? = task.result
                    if (account != null) {
                        firebaseAuthWithGoogle(account)
                    }
                } else {
                    pokedexViewModel.onLoginCancel()
                    Toast.makeText(context, task.exception.toString(), Toast.LENGTH_SHORT).show()
                    Log.e("Login", task.exception.toString())
                }
            }
            if (result.resultCode == Activity.RESULT_CANCELED) {
                pokedexViewModel.onLoginCancel()
                binding.loginProgress.visibility = View.GONE
            }
        }




    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                pokedexViewModel.onLoginComplete()
                binding.loginProgress.visibility = View.GONE
                Log.i("Login", "Login Success!")
            } else {
                pokedexViewModel.onLoginCancel()
                binding.loginProgress.visibility = View.GONE
                Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()
                Log.e("Login", it.exception.toString())

            }
        }
    }




    private fun initOnClickListeners() {
        binding.privacyTV.setOnClickListener { Toast.makeText(context,"Privacy Policy",Toast.LENGTH_SHORT).show() }
        binding.termsTV.setOnClickListener { Toast.makeText(context,"Terms of Service",Toast.LENGTH_SHORT).show() }

    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
        }
    }

}