package com.example.composition.prisentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.composition.R
import com.example.composition.databinding.FrgmentGameBinding

class GameFragment: Fragment() {

   private var _binding:FrgmentGameBinding? = null
    private val binding:FrgmentGameBinding
    get() = _binding?:throw RuntimeException("GameFragment == null")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FrgmentGameBinding.inflate(inflater,container,false)
       return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}