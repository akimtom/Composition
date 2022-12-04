package com.example.composition.prisentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.composition.R
import com.example.composition.databinding.FragmetWelcomeBinding
import ru.sumin.jetpackstart.presentation.ChooseLevelFragment

class WelcomeFragment:Fragment()
{
    private  var _binding: FragmetWelcomeBinding? = null
    private val binding: FragmetWelcomeBinding
      get() = _binding?:throw RuntimeException(" FragmetWelcomeBinding == null")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmetWelcomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    binding.buttonUnderstand.setOnClickListener {
        choseFragmentLevelRunning()
    }
    }
    private fun choseFragmentLevelRunning()
    {
        requireActivity().supportFragmentManager.beginTransaction().
        addToBackStack(ChooseLevelFragment.CHOOSE_FRAGMENT_NAME).
        replace(R.id.main_container,ChooseLevelFragment.newInstance()).
        commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}