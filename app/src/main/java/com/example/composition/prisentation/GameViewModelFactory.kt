package com.example.composition.prisentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.composition.domain.Entity.Level

class GameViewModelFactory(
    private val level: Level,
    private val application: Application
):ViewModelProvider.Factory

{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(application, level) as T
        }
        else
        {
            throw RuntimeException("Not right model $modelClass for this factory")
        }
    }
}