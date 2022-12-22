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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composition.databinding.FrgmentGameBinding

class GameFragment : Fragment() {
    private  val args by navArgs<GameFragmentArgs>()
    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(this,
            GameViewModelFactory(args.level,requireActivity().application))[GameViewModel::class.java]
    }
    private var _binding: FrgmentGameBinding?=null
    private val binding: FrgmentGameBinding
        get() = _binding?:throw RuntimeException("FragmentGameFinishedBinding = null")



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
     //   setClickListenersToOptions()
        binding.viewModel=viewModel
        binding.lifecycleOwner=viewLifecycleOwner
    }

    /*private fun setClickListenersToOptions()
    {
        for (tvOption in tvOptions)
        {
            tvOption.setOnClickListener {
                viewModel.chooseAnswer(tvOption.text.toString().toInt())
            }
        }

    }*/
    private fun observeViewModel()
    {

        viewModel.gameResult.observe(viewLifecycleOwner)
        {

             findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameFinishFragment(it))
        }





    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }







}