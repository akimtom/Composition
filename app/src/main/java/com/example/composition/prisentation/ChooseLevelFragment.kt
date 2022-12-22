package com.example.composition.prisentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.composition.databinding.ActivityMainBinding.inflate
import com.example.composition.databinding.ChoseLevelFragmetnBinding
import com.example.composition.domain.entity.Level


class ChooseLevelFragment : Fragment() {

    private var _binding: ChoseLevelFragmetnBinding?=null
    private val binding: ChoseLevelFragmetnBinding
        get() = _binding?:throw RuntimeException("ChooseLevelFragment = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding= ChoseLevelFragmetnBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectLevelClick()
    }

    private fun selectLevelClick() {
        with(binding){
            bTest.setOnClickListener {
                launchGameFragment(Level.TEST)
            }
            bEasy.setOnClickListener {
                launchGameFragment(Level.EASY)
            }
            bMidl.setOnClickListener {
                launchGameFragment(Level.NORMAL)
            }
            bHard.setOnClickListener {
                launchGameFragment(Level.HARD)
            }
        }

    }
    private fun launchGameFragment(level: Level)
    {


        findNavController().navigate(ChooseLevelFragmentDirections.actionChooseLevelFragmentToGameFragment(level))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}
