package com.example.composition.domain.Entity

data class GameResult(
 val winner:Boolean,
 val countRightAnswers:Int,
 val countTotalAnswers: Int,
 val gameSittings: GameSittings
) {


}