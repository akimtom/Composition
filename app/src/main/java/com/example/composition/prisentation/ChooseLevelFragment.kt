package ru.sumin.jetpackstart.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.fragment.app.Fragment
import com.example.composition.R
import com.example.composition.databinding.ActivityMainBinding.inflate
import com.example.composition.databinding.ChoseLevelFragmetnBinding
import com.example.composition.databinding.FragmentGameFinishedBinding


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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
