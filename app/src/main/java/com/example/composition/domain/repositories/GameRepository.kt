package com.example.composition.domain.repositories

import com.example.composition.domain.Entity.GameSittings
import com.example.composition.domain.Entity.Level
import com.example.composition.domain.Entity.Question

interface GameRepository {
    fun generateQuestion(maxSumValue:Int,countOfOptions:Int):Question
    fun getGameSittings(level: Level ):GameSittings

}