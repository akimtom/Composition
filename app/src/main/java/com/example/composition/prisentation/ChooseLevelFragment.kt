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
import com.example.composition.domain.Entity.Level
import com.example.composition.prisentation.GameFragment


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
                requireActivity().supportFragmentManager.beginTransaction().
                addToBackStack(GameFragment.GAME_FRAGMENT).
                replace(R.id.main_container,GameFragment.newInstance(Level.TEST)).
                commit()
            }
            bEasy.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction().
                addToBackStack(GameFragment.GAME_FRAGMENT).
                replace(R.id.main_container,GameFragment.newInstance(Level.EASY)).
                commit()
            }
            bMidl.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction().
                addToBackStack(GameFragment.GAME_FRAGMENT).
                replace(R.id.main_container,GameFragment.newInstance(Level.NORMAL)).
                commit()
            }
            bHard.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction().
                addToBackStack(GameFragment.GAME_FRAGMENT).
                replace(R.id.main_container,GameFragment.newInstance(Level.HARD)).
                commit()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object
    {
        const val CHOOSE_FRAGMENT_NAME = "ChooseLevelFragment"
        fun newInstance( ):ChooseLevelFragment
        {

            return ChooseLevelFragment()

        }
    }

}
