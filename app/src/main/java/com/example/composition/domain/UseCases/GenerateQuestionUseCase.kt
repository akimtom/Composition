package com.example.composition.domain.UseCases

import com.example.composition.domain.Entity.GameSittings
import com.example.composition.domain.Entity.Question
import com.example.composition.domain.repositories.GameRepository

class GenerateQuestionUseCase(private val repository: GameRepository)
{
    operator fun invoke(maxSumValue: Int):Question
    {
     return   repository.generateQuestion(maxSumValue, COUNT_OF_OPTIONS)
    }
    private companion object
    {
        private const val COUNT_OF_OPTIONS = 6
    }
}