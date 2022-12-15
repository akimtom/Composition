package com.example.composition.prisentation

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composition.R
import com.example.composition.databinding.FrgmentGameBinding
import com.example.composition.domain.Entity.Level

class GameFragment : Fragment() {
    private  val args by navArgs<GameFragmentArgs>()
    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(this,
            GameViewModelFactory(args.level,requireActivity().application))[GameViewModel::class.java]
    }
    private var _binding: FrgmentGameBinding?=null
    private val binding: FrgmentGameBinding
        get() = _binding?:throw RuntimeException("FragmentGameFinishedBinding = null")




    private val tvOptions by lazy {

        mutableListOf<TextView>().apply{
            add(binding.tvOption1)
            add(binding.tvOption2)
            add(binding.tvOption3)
            add(binding.tvOption4)
            add(binding.tvOption5)
            add(binding.tvOption6)
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FrgmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setClickListenersToOptions()

    }

    private fun setClickListenersToOptions()
    {
        for (tvOption in tvOptions)
        {
            tvOption.setOnClickListener {
                viewModel.chooseAnswer(tvOption.text.toString().toInt())
            }
        }

    }
    private fun observeViewModel()
    {
        viewModel.question.observe(viewLifecycleOwner)
        {
            binding.tvSum.text = it.sum.toString()
            binding.tvLeftNumber.text = it.visibleNumber.toString()
            for(i in 0 until tvOptions.size )
            {
                tvOptions[i].text = it.options[i].toString()
            }
        }

        viewModel.percentOfRightAnswers.observe(viewLifecycleOwner)
        {
            binding.progressBar.setProgress(it, true)


        }
        viewModel.enoughPresentOfRightAnswers.observe(viewLifecycleOwner)
        {


            binding.tvAnsversProgres.setTextColor(getColorByState(it))
        }
        viewModel.enoughPresentOfRightAnswers.observe(viewLifecycleOwner)
        {
            val color = getColorByState(it)
            binding.progressBar.progressTintList = ColorStateList.valueOf(color)
        }

        viewModel.leftFormattedTime.observe(viewLifecycleOwner)
        {
            binding.tvTimer.text = it.toString()
        }
        viewModel.minPercentOfRightAnswers.observe(viewLifecycleOwner)
        {
            binding.progressBar.secondaryProgress = it
        }
        viewModel.gameResult.observe(viewLifecycleOwner)
        {

             findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameFinishFragment(it))
        }
        viewModel.progressedAnswers.observe(viewLifecycleOwner)
        {
            binding.tvAnsversProgres.text = it
        }




    }

    private fun getColorByState(goodState: Boolean):Int
    {
        val colorResId = if(goodState)
        {
            android.R.color.holo_green_light
        }
        else
        {
            android.R.color.holo_red_light
        }
        return  ContextCompat.getColor(requireContext(),colorResId)
    }






    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }







}