package dev.shreyansh.pokemon.pokedex.ui.home

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.canhub.cropper.options
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.mlkit.common.model.LocalModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeler
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.custom.CustomImageLabelerOptions
import dagger.hilt.android.AndroidEntryPoint
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentHomeBinding
import dev.shreyansh.pokemon.pokedex.domain.PokemonNews
import dev.shreyansh.pokemon.pokedex.domain.Scan
import dev.shreyansh.pokemon.pokedex.utils.PokeNewsRecyclerAdapter
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var levels = 0
    private var firstName : String = ""
    private lateinit var labeler : ImageLabeler
    private lateinit var localModel: LocalModel
    private lateinit var binding : FragmentHomeBinding
    private lateinit var pokeNewsRecyclerAdapter : PokeNewsRecyclerAdapter
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        setStatusBarColor()

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        binding.viewModel = pokedexViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initUser()
        getPokemonNews()
        setupPokeNewsRecyclerView()
        setupObservers()
        setupOnClickListeners()

        return binding.root
    }


    private fun setStatusBarColor() {
        when(requireContext().resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)){
            Configuration.UI_MODE_NIGHT_YES -> requireActivity().window.statusBarColor = Color.parseColor("#000000")
            Configuration.UI_MODE_NIGHT_NO -> requireActivity().window.statusBarColor = Color.parseColor("#ffffff")
            else -> requireActivity().window.statusBarColor = Color.parseColor("#000000")
        }
    }


    private fun setupOnClickListeners() {
        binding.moreNewsTV.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNewsFragment())
        }
        binding.pokemonCV.setOnClickListener {
            pokedexViewModel.updatePokemonFilter("all")
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPokemonDirectoryContainerFragment())
        }
        binding.movesCV.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMovesFragment())
        }
        binding.abilityCV.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAbilitiesFragment())
        }
        binding.itemsCV.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToItemsFragment())
        }
        binding.locationCV.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLocationsFragment())
        }
        binding.typesCV.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToTypesFragment())
        }
        binding.quizCV.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToQuizIntroFragment())
        }
        binding.levelsStatusCV.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLevelsFragment())
        }
        binding.search.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchContainerFragment())
        }
        binding.menu.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMenuFragment())
        }
        binding.pokeScanButton.setOnClickListener {
            loadModelAndLabeler()
            scanInit()
        }

    }


    private fun getPokemonNews() {
        pokedexViewModel.getPokeNews()
    }


    private fun setupPokeNewsRecyclerView() {
        pokeNewsRecyclerAdapter = PokeNewsRecyclerAdapter(PokeNewsRecyclerAdapter.OnClickListener{
            navigateToNewsItem(it)
        }, requireActivity())
        binding.pokemonNewsRV.adapter = pokeNewsRecyclerAdapter
    }

    private fun checkDarkMode(){
        pokedexViewModel.appTheme.observe(viewLifecycleOwner, Observer {
            when(it){
                "Light Mode" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                "Dark Mode" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                "System Default" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        })

    }

    private fun setupObservers() {
        pokedexViewModel.pokeNewsResponse.observe(viewLifecycleOwner, Observer {
            it?.let {
                pokeNewsRecyclerAdapter.submitList(it.take(25).toMutableList())
            }
        })
        pokedexViewModel.level.observe(viewLifecycleOwner, Observer {
            it?.let {
                levels = it
                when(it){
                    0 -> binding.levelsTV.text = "Beginner"
                    in 1..9 -> binding.levelsTV.text = "Learner"
                    in 10..24 -> binding.levelsTV.text = "Seeker"
                    in 25..49 -> binding.levelsTV.text = "Explorer"
                    in 50..99 -> binding.levelsTV.text = "Scholar"
                    in 100..249 -> binding.levelsTV.text = "Savant"
                    else -> binding.levelsTV.text = "Sage"
                }
            }
        })
    }

    private fun initUser() {
        firstName = FirebaseAuth.getInstance().currentUser?.displayName?.split("\\s".toRegex())?.toTypedArray()?.get(0).toString()
        binding.userName.text = "Hey, ${firstName}! 👋"
        Glide.with(requireContext())
            .load(FirebaseAuth.getInstance().currentUser?.photoUrl)
            .placeholder(R.drawable.profile)
            .error(R.drawable.profile)
            .into(binding.userProfilePic);
    }

    private fun navigateToNewsItem(news: PokemonNews) {
        val navigateIntent = Intent(Intent.ACTION_VIEW)
        navigateIntent.data = Uri.parse("https://api.pokemon.com${news.url}")
        startActivity(navigateIntent)
    }



    // poke-scan
    private fun loadModelAndLabeler(){
        localModel = LocalModel.Builder()
            .setAssetFilePath("pokedex.tflite")
            .build()
        val customImageLabelerOptions = CustomImageLabelerOptions.Builder(localModel)
            .setConfidenceThreshold(0.5f)
            .setMaxResultCount(5)
            .build()

        labeler = ImageLabeling.getClient(customImageLabelerOptions)
    }
    private fun scanInit() {
        activityResultLauncher.launch(
            arrayOf(android.Manifest.permission.CAMERA)
        )
    }

    private val activityResultLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        // Handle Permission granted/rejected
        val allPermissionGranted : MutableList<Boolean> = mutableListOf()
        permissions.entries.forEach {
            if(!it.value){
                Toast.makeText(context,"Enable Permission : ${it.key}", Toast.LENGTH_SHORT).show()
            }
            allPermissionGranted.add(it.value)
        }
        if(allPermissionGranted[0]){
            captureImage()
        }
    }

    private val cropImage = registerForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            val uriContent = result.uriContent
            if (uriContent != null) {
                scanPokemon(uriContent)
            }
        } else {
            Log.e("Capture Fragment","${result.error.toString()}")
        }
    }

    private fun captureImage() {
        cropImage.launch(
            options() {
                setGuidelines(CropImageView.Guidelines.ON)
                setOutputCompressFormat(Bitmap.CompressFormat.PNG)
            }
        )
    }


    private fun scanPokemon(uriContent: Uri){
        try{
            val scanDialog = MaterialAlertDialogBuilder(requireContext())
                .setTitle("Poké-Scan")
                .setMessage("Uh Oh, the provided image did no match any pokemon(s) in our pokédex database!")
                .setNegativeButton("Okay") { _, _ ->
                }
                .setPositiveButton("Try Again") { _, _ ->
                    scanInit()
                }

            val inputImage = InputImage.fromFilePath(requireContext(),uriContent)
            labeler.process(inputImage)
                .addOnSuccessListener { labels ->
                    val detectedPokemons : MutableList<Scan> = mutableListOf()
                    for (label in labels) {
                        detectedPokemons.add(Scan(label.text,label.confidence,label.index))
                    }
                    if(detectedPokemons.isNullOrEmpty()){
                        scanDialog.show()
                    }else{
                        var selectedPokemon = ""
                        for(pokemon in detectedPokemons){
                            if(pokemon.confidence > 0.92){
                                selectedPokemon = pokemon.name
                            }
                        }
                        if(selectedPokemon != ""){
                            pokedexViewModel.searchPokemon(selectedPokemon).observe(viewLifecycleOwner, Observer {
                                if(!it.isNullOrEmpty()){
                                    pokedexViewModel.setLevel(levels+1)
                                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPokemonDetailFragment(it[0]))
                                }else{
                                    scanDialog.show()
                                }
                            })
                        }else{
                            scanDialog.show()
                        }
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(context,"Error Occurred : ${e.message.toString()}", Toast.LENGTH_SHORT).show()
                }
        }
        catch (e: Exception){
            Toast.makeText(context,"Error Occurred : ${e.message.toString()}", Toast.LENGTH_SHORT).show()
        }
    }



}