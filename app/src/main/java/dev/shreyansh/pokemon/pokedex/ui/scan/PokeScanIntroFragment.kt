package dev.shreyansh.pokemon.pokedex.ui.scan

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.canhub.cropper.options
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.mlkit.common.model.LocalModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeler
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.custom.CustomImageLabelerOptions
import dagger.hilt.android.AndroidEntryPoint
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentPokeScanIntroBinding
import dev.shreyansh.pokemon.pokedex.domain.Scan
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory
import timber.log.Timber

@AndroidEntryPoint
class PokeScanIntroFragment : DialogFragment() {

    private var levels = 0
    private lateinit var labeler : ImageLabeler
    private lateinit var localModel: LocalModel
    private lateinit var binding : FragmentPokeScanIntroBinding
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.ChatOptionsStyle)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_poke_scan_intro, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        setupObservers()
        setupOnClickListeners()

        return binding.root
    }


    private fun setupObservers() {
        pokedexViewModel.level.observe(viewLifecycleOwner, Observer {
            it?.let {
                levels = it
            }
        })
    }

    private fun setupOnClickListeners() {
        binding.checkOutButton.setOnClickListener {
            loadModelAndLabeler()
            scanInit()
        }
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
            Timber.e("Capture Fragment","${result.error.toString()}")
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
                                    findNavController().navigate(PokeScanIntroFragmentDirections.actionPokeScanIntroFragmentToPokemonDetailFragment(it[0]))
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