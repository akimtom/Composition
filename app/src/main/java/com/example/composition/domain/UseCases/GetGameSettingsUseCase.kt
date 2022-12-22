package com.example.composition.domain.UseCases

import com.example.composition.domain.entity.GameSittings
import com.example.composition.domain.entity.Level
import com.example.composition.domain.repositories.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {
    operator fun invoke(level: Level):GameSittings
    {
        return  repository.getGameSittings(level)
    }
}