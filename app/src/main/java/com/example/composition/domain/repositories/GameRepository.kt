package com.example.composition.domain.repositories

import com.example.composition.domain.entity.GameSittings
import com.example.composition.domain.entity.Level
import com.example.composition.domain.entity.Question

interface GameRepository {
    fun generateQuestion(maxSumValue:Int,countOfOptions:Int):Question
    fun getGameSittings(level: Level ):GameSittings

}