package com.example.composition.prisentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.composition.R
import com.example.composition.databinding.FrgmentGameBinding
import com.example.composition.domain.Entity.GameResult
import com.example.composition.domain.Entity.GameSittings
import com.example.composition.domain.Entity.Level
import ru.sumin.jetpackstart.presentation.ChooseLevelFragment

class GameFragment: Fragment() {

    private lateinit var level: Level
   private var _binding:FrgmentGameBinding? = null
    private val binding:FrgmentGameBinding
    get() = _binding?:throw RuntimeException("GameFragment == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FrgmentGameBinding.inflate(inflater,container,false)
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvSum.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction().
            addToBackStack(null).
            replace(R.id.main_container, GameFinishFragment.
            newInstance(GameResult(true,12,32,
                GameSittings(3,5,2,4)))).
            commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs()
    {
     requireArguments().getParcelable<Level>(KEY_LVL)?.let {
         level = it
     }
    }
    companion object{
        private const val KEY_LVL = "level"
         const val GAME_FRAGMENT = "g_fragment"
        fun newInstance(level: Level):GameFragment
        {

            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LVL,level)
                }

            }

        }
    }

}