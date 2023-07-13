package dev.shreyansh.pokemon.pokedex.ui.quiz

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentQuizBinding
import dev.shreyansh.pokemon.pokedex.domain.Quiz
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory


class QuizFragment : Fragment() {

    private var quesIndex = 0
    private var totalScore = 0
    private lateinit var currentQues : Quiz
    private lateinit var quizRepo : MutableList<Quiz>
    private lateinit var quizSession : MutableList<Quiz>
    private lateinit var binding : FragmentQuizBinding
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_quiz, container, false)

        binding.viewModel = pokedexViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setupObservers()
        setupOnClickListeners()
        return binding.root
    }

    private fun initQuiz() {
        if(!quizRepo.isNullOrEmpty()){
            quizSession = quizRepo.shuffled().toMutableList().take(5).toMutableList()
            animateProgressBar(25)
            setQuestion()
        }
    }

    private fun setQuestion() {
        currentQues = quizSession[quesIndex]
        binding.quesTV.text = currentQues.question.toString()
        binding.quesNumberTV.text = "Question ${quesIndex+1}/5"
        binding.option1TV.text = "A.  ${currentQues.options[0].toString()}"
        binding.option2TV.text = "B.  ${currentQues.options[1].toString()}"
        binding.option3TV.text = "C.  ${currentQues.options[2].toString()}"
        binding.option4TV.text = "D.  ${currentQues.options[3].toString()}"
    }

    private fun setupObservers() {
        pokedexViewModel.allPokemonQuiz.observe(viewLifecycleOwner, Observer {
            if(it.isNullOrEmpty()){
                pokedexViewModel.getAllQuiz()
            }else{
                quizRepo = it.toMutableList()
                binding.quizNestedScrollView.visibility = View.VISIBLE
                initQuiz()
            }
        })
    }


    private fun setupOnClickListeners() {
        binding.option1CV.setOnClickListener {
            handleResponse(currentQues.options[0],binding.option1CV)
        }
        binding.option2CV.setOnClickListener {
            handleResponse(currentQues.options[1],binding.option2CV)
        }
        binding.option3CV.setOnClickListener {
            handleResponse(currentQues.options[2],binding.option3CV)
        }
        binding.option4CV.setOnClickListener {
            handleResponse(currentQues.options[3],binding.option4CV)
        }
        binding.nextQuesButton.setOnClickListener {
            handleNextQuestion()
        }
        binding.quizToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun handleResponse(selectedAns: String, selectedCV: MaterialCardView){
        if(selectedAns == currentQues.answer){
            totalScore += 2
            binding.confetti.visibility = View.VISIBLE
            binding.answerTV.text = "Yes, that's correct! 😄🎊"
            selectedCV.strokeColor = Color.parseColor("#38761d")
            selectedCV.setCardBackgroundColor(Color.parseColor("#d9ead3"))

        }else{
            binding.answerTV.text = "Uh Oh, that's incorrect! 🙁"
            selectedCV.strokeColor = Color.parseColor("#990000")
            selectedCV.setCardBackgroundColor(Color.parseColor("#f4cccc"))
        }
        binding.answerTV.visibility = View.VISIBLE
        binding.nextQuesButton.visibility = View.VISIBLE
        binding.option1CV.isClickable = false
        binding.option2CV.isClickable = false
        binding.option3CV.isClickable = false
        binding.option4CV.isClickable = false


        if(quesIndex == 4){
            if(totalScore >=2 ){
                binding.scoreTV.text = "Congratulations, you've gained ${totalScore} more pokemon trainer-level points! 🍾"
            }else{
                binding.scoreTV.text = "Whoops, you failed to gain any pokemon trainer-level points. Try again in next 24 hours!"
            }
            binding.nextQuesButton.postDelayed(Runnable { binding.scoreTV.visibility = View.VISIBLE } , 400)
            binding.nextQuesButton.text = "Check Your Trainer Level"
            setQuizCoolDown()
        }
    }


    private fun handleNextQuestion() {

        if(quesIndex != 4){
            quesIndex += 1
            binding.answerTV.visibility = View.GONE
            binding.nextQuesButton.visibility = View.GONE
            binding.option1CV.isClickable = true
            binding.option2CV.isClickable = true
            binding.option3CV.isClickable = true
            binding.option4CV.isClickable = true
            setQuestion()
            resetCardBackground()
            setProgressBars()
        }

        binding.confetti.visibility = View.GONE




    }

    private fun setProgressBars() {
        animateProgressBar(20 + quesIndex*20)
        when(quesIndex){
            0 -> {
                binding.completionIV.setImageResource(R.drawable.circle1)
                binding.messageTV.text = "Let's Go!"
            }
            1 -> {
                binding.completionIV.setImageResource(R.drawable.circle2)
                binding.messageTV.text = "Time to shine!"
            }
            2 -> {
                binding.completionIV.setImageResource(R.drawable.circle3)
                binding.messageTV.text = "Rise to the challenge!"
            }
            3 -> {
                binding.completionIV.setImageResource(R.drawable.circle4)
                binding.messageTV.text = "Let's make history!"
            }
            4 -> {
                binding.completionIV.setImageResource(R.drawable.circle5)
                binding.messageTV.text = "Claim your victory!"
            }

        }
    }

    private fun resetCardBackground() {
        binding.option1CV.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
        binding.option1CV.strokeColor = Color.parseColor("#B5B5B5")

        binding.option2CV.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
        binding.option2CV.strokeColor = Color.parseColor("#B5B5B5")

        binding.option3CV.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
        binding.option3CV.strokeColor = Color.parseColor("#B5B5B5")

        binding.option4CV.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
        binding.option4CV.strokeColor = Color.parseColor("#B5B5B5")
    }


    private fun animateProgressBar(targetProgress: Int) {
        val animation = ObjectAnimator.ofInt(binding.quizCompletionProgress, "progress", targetProgress)
        animation.duration = 500
        animation.start()
    }

    private fun setQuizCoolDown(){
        val currentTimeMillis = System.currentTimeMillis()
        val coolDown: Long = 24 * 60 * 60 * 1000
        pokedexViewModel.setQuizCoolDown(currentTimeMillis+coolDown)
    }
}