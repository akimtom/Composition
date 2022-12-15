package com.example.composition.prisentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composition.R
import com.example.composition.databinding.FragmentGameFinishedBinding
import com.example.composition.domain.Entity.GameResult

class GameFinishFragment:Fragment() {
    private  val args by navArgs<GameFinishFragmentArgs>()

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


        binding.tvRequiredAnswers.text =
            String.format(requireContext().resources.getString(R.string.required_score),args.gameresult.gameSittings.minCountOfRightAnswers)
        binding.tvScoreAnswers.text=
            String.format(requireContext().resources.getString(R.string.required_score),args.gameresult.countRightAnswers)
        binding.tvRequiredPercentage.text=
            String.format(requireContext().resources.getString(R.string.required_percentage),args.gameresult.gameSittings.minPercentOfRightAnswers)
        /*binding.tvScorePercentage.text=
            String.format(requireContext().resources.getString(R.string.score_percentage),args.gameresult.gameSittings.*/
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
        if(args.gameresult.gameSittings.minCountOfRightAnswers<=args.gameresult.countRightAnswers)
        {
            binding.emojiResult.setImageResource(R.drawable.ic_smile)
        }
        else
        {
            binding.emojiResult.setImageResource(R.drawable.ic_sad)
        }



    }
    private fun retryGame()
    {
        findNavController().popBackStack()
         }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}