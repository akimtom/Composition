package com.example.composition.domain.UseCases

import com.example.composition.domain.Entity.GameSittings
import com.example.composition.domain.Entity.Level
import com.example.composition.domain.repositories.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {
    operator fun invoke(level: Level):GameSittings
    {
        return  repository.getGameSittings(level)
    }
}