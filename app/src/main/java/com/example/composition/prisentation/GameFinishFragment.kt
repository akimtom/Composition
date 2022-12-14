package com.example.composition.prisentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.composition.R
import com.example.composition.databinding.FragmentGameFinishedBinding
import com.example.composition.databinding.FrgmentGameBinding
import com.example.composition.domain.Entity.GameResult
import ru.sumin.jetpackstart.presentation.ChooseLevelFragment

class GameFinishFragment:Fragment() {
    private lateinit var gameResult: GameResult
   private var _binding:FragmentGameFinishedBinding?=null
   private val binding:FragmentGameFinishedBinding
    get() = _binding?:throw RuntimeException("FragmentGameFinishedBinding = null")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentGameFinishedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireArguments().getParcelable<GameResult>(GAME_RESULT)?.let {
            gameResult = it
        }

        binding.tvRequiredAnswers.text =
            String.format(requireContext().resources.getString(R.string.required_score),gameResult.gameSittings.minCountOfRightAnswers)
        binding.tvScoreAnswers.text=
            String.format(requireContext().resources.getString(R.string.required_score),gameResult.countRightAnswers)
        binding.tvRequiredPercentage.text=
            String.format(requireContext().resources.getString(R.string.required_percentage),gameResult.gameSittings.minPercentOfRightAnswers)
        /*binding.tvScorePercentage.text=
            String.format(requireContext().resources.getString(R.string.score_percentage),gameResult.gameSittings.*/
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
        if(gameResult.gameSittings.minCountOfRightAnswers<=gameResult.countRightAnswers)
        {
            binding.emojiResult.setImageResource(R.drawable.ic_smile)
        }
        else
        {
            binding.emojiResult.setImageResource(R.drawable.ic_sad)
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                retryGame()
            }

        })


    }
    private fun retryGame()
    {
        requireActivity().supportFragmentManager.popBackStack(GameFragment.GAME_FRAGMENT, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        private const val GAME_RESULT = "g_result"
        fun newInstance(gameResult: GameResult):GameFinishFragment
        {
        return    GameFinishFragment().apply {
            arguments = Bundle().apply {
                putParcelable(GAME_RESULT,gameResult)
            }
        }
        }
    }
}